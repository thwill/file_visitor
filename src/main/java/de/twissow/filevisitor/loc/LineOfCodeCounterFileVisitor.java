package de.twissow.filevisitor.loc;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;

@Slf4j
@Getter
public class LineOfCodeCounterFileVisitor implements FileVisitor<Path> {

    private int countFacelets;
    private int countClasses;
    private int lineOfCodes;

    @Override public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        log.info("visitFile " + file.getFileName());
        String extension = FilenameUtils.getExtension(file.toFile().getName());
        if (extension.equals("java")) {
            countClasses++;
            List<String> fileContent = Files.readAllLines(file, Charset.forName("Cp1252"));
            lineOfCodes += fileContent.size();
            return FileVisitResult.CONTINUE;
        } else if (extension.equals("xhtml")) {
            countFacelets++;
            return FileVisitResult.CONTINUE;
        } else {
            return FileVisitResult.CONTINUE;
        }
    }

    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        log.info("preVisitDirectory " + dir.getFileName());
        return FileVisitResult.CONTINUE;
    }

    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
        log.info("visitFileFailed" + file.getFileName());
        return FileVisitResult.CONTINUE;
    }

    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
        log.info("postVisitDirectory " + dir.getFileName());
        return FileVisitResult.CONTINUE;
    }
}
