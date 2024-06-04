package io.github.wbdsjunior.fulfillment.orders;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
public class FileNormalizerService {

    public void normalize(final File file) throws IOException {

        Files.lines(file.toPath())
                .sorted(String::compareTo)
                .map(FileLineBuyer::from)
                .collect(Collectors.toList());

    }
}
