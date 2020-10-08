package de.twissow.filevisitor.utf8convert;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DoIt {



    public static void main(String[] args) throws Exception {
        Path startingDir = Paths.get("C:/Users/twill1/Projekte/sam/git-repos/sam_new");
        //Path startingDir = Paths.get("C:/tmp");
        NonAsciiCharRemoverFileVisitor nonAsciiCharRemoverFileVisitor = new NonAsciiCharRemoverFileVisitor();
        Files.walkFileTree(startingDir,nonAsciiCharRemoverFileVisitor);

    }

}
