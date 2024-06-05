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
                                  buyerFound -> {

                                        try {

                                            buyerFound.add(fileLineBuyer.order());
                                        } catch (DuplicatedBuyerOrderProductException e) {

                                            throw new DuplicatedBuyerOrderProductException(
                                                      String.format(
                                                              "Duplicated order product for buyer {id=%d}"
                                                            , fileLineBuyer.id()
                                                        )
                                                    , e
                                                );
                                        }
                                    }
                                , () -> buyers.add(BuyerDto.from(fileLineBuyer))
                            )
                    );
         return buyers;
    }
}
