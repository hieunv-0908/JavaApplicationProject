package re.java_application_project_final.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import re.java_application_project_final.exception.InsufficientStockException;
import re.java_application_project_final.model.entity.*;
import re.java_application_project_final.repository.*;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DispenseService {

    private final PrescriptionRepository
            prescriptionRepository;

    private final PrescriptionDetailRepository
            prescriptionDetailRepository;

    private final MedicineRepository
            medicineRepository;

    @Transactional
    public void dispensePrescription(
            Long prescriptionId
    ) {

        Prescription prescription =
                prescriptionRepository
                        .findById(prescriptionId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Prescription not found"
                                )
                        );

        if (prescription.getStatus()
                == PrescriptionStatus.DISPENSED) {

            throw new RuntimeException(
                    "Prescription already dispensed"
            );
        }

        List<PrescriptionDetail> details =
                prescriptionDetailRepository
                        .findByPrescription(
                                prescription
                        );

        /*
            STEP 1
            CHECK ALL STOCK
         */

        for (PrescriptionDetail detail
                : details) {

            Medicine medicine =
                    detail.getMedicine();

            if (medicine.getQuantity()
                    < detail.getQuantity()) {

                throw new InsufficientStockException(

                        "Không đủ thuốc: "
                                + medicine.getName()

                );
            }
        }

        /*
            STEP 2
            DEDUCT STOCK
         */

        for (PrescriptionDetail detail
                : details) {

            Medicine medicine =
                    detail.getMedicine();

            medicine.setQuantity(

                    medicine.getQuantity()
                            - detail.getQuantity()

            );

            medicineRepository.save(medicine);
        }

        /*
            STEP 3
            UPDATE STATUS
         */

        prescription.setStatus(
                PrescriptionStatus.DISPENSED
        );

        prescriptionRepository.save(
                prescription
        );
    }
}