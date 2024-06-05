package io.github.wbdsjunior.fulfillment.orders;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

import io.github.wbdsjunior.fulfillment.orders.usecase.NormalizerService;

@Service
public class FileNormalizerService implements NormalizerService<File, Set<BuyerDto>>{

    public Set<BuyerDto> normalize(final File file) throws IOException {

        var buyers = new HashSet<BuyerDto>();

        try (var fileLines = Files.lines(file.toPath())) {

            fileLines.map(FileLineBuyer::from)
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
                        ));
        }
        return buyers;
    }
}
