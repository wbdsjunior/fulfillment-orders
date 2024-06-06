package io.github.wbdsjunior.fulfillment.orders;

import java.io.File;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import io.github.wbdsjunior.fulfillment.orders.persistence.JpaBuyerEntity;
import io.github.wbdsjunior.fulfillment.orders.persistence.SpringDataJpaBuyerRepository;

@Service
public class SaveBuyerService {

    private final NormalizerService<File, Set<BuyerDto>> normalizerService;
    private final SpringDataJpaBuyerRepository buyerRepository;

    public SaveBuyerService(
              final NormalizerService<File, Set<BuyerDto>> normalizerService
            , final SpringDataJpaBuyerRepository buyerRepository
        ) {

        this.normalizerService = normalizerService;
        this.buyerRepository = buyerRepository;
    }

    public Set<BuyerDto> save(final File file) throws Exception {

        var buyers = normalizerService.normalize(file);
        buyerRepository.saveAll(buyers.stream()
                .map(JpaBuyerEntity::from)
                .collect(Collectors.toList()));
        return buyers;
    }
}
