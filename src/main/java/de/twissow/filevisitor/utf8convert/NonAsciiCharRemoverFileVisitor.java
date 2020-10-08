package de.twissow.filevisitor.utf8convert;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;

@Slf4j
public class NonAsciiCharRemoverFileVisitor implements FileVisitor<Path> {

    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        log.info("preVisitDirectory " + dir.getFileName());
        return FileVisitResult.CONTINUE;
    }

    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        log.info("visitFile " + file.getFileName());
        String extension = FilenameUtils.getExtension(file.toFile().getName());
        if (extension.equals("java")) {
            removeNonAsciiCharsFromFile(file);
            return FileVisitResult.CONTINUE;
        } else {
            return FileVisitResult.CONTINUE;
        }
    }

    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
        log.info("visitFileFailed" + file.getFileName());
        return FileVisitResult.CONTINUE;
    }

    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
        log.info("visitFileFailed " + dir.getFileName());
        return FileVisitResult.CONTINUE;
    }


    private void removeNonAsciiCharsFromFile(Path file) throws IOException {
        log.info(file.getFileName().toString());
        List<String> fileContent = Files.readAllLines(file, Charset.forName("Cp1252"));
        for (int i = 0; i < fileContent.size(); i++) {
            String oldLine = fileContent.get(i);
            String newLine = oldLine.replaceAll("[^\\x00-\\x7F]", "");
            //log.info(newLine);
            fileContent.set(i, newLine);

        }
        Files.write(file, fileContent, StandardCharsets.UTF_8);
    }


}
