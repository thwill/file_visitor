package de.twissow.filevisitor.loc;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CountIt {

    public static void main(String[] args) throws Exception {
        Path startingDir = Paths.get("C:/Users/twill1/Projekte/sam/git-repos/sam_new");
        //Path startingDir = Paths.get("C:/Users/twill1/Projekte/sam/git-repos/sam_rest");
        LineOfCodeCounterFileVisitor locFileVisitor = new LineOfCodeCounterFileVisitor();
        Files.walkFileTree(startingDir,locFileVisitor);
        System.out.println("facelets:  " + locFileVisitor.getCountFacelets());
        System.out.println("classes:  " + locFileVisitor.getCountClasses());
        System.out.println("line of codes:  " + locFileVisitor.getLineOfCodes());
    }

}
