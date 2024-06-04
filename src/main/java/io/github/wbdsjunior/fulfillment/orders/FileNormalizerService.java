package io.github.wbdsjunior.fulfillment.orders;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

@Service
public class FileNormalizerService {

    public Set<BuyerDto> normalize(final File file) throws IOException {

        var buyers = new HashSet<BuyerDto>();
        Files.lines(file.toPath())
                .map(FileLineBuyer::from)
                .forEach(fileLineBuyer -> buyers.stream()
                        .filter(existingBuyer -> existingBuyer.id() == fileLineBuyer.id())
                        .findFirst()
                        .ifPresentOrElse(
                                  buyerFound -> buyerFound.add(fileLineBuyer.order())
                                , () -> buyers.add(BuyerDto.from(fileLineBuyer))
                            )
                    );
         return buyers;
    }
}
