package edu.curtin.app.view;

import edu.curtin.app.model.Railways;
import edu.curtin.app.model.Town;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Generates DOT file showing the town and railway network.
 */
public class DotOutputGenerator {
    public void generateDot(Map<String, Town> towns, List<Railways> railways) {
        try (FileWriter fw = new FileWriter("simoutput.dot")) {
            fw.write("graph Towns {\n");
            for (String name : towns.keySet()) {
                fw.write("    " + name + "\n");
            }
            for (Railways r : railways) {
                fw.write("    " + r.getDotLine() + "\n");
            }
            fw.write("}\n");
        } catch (IOException e) {
            System.err.println("Failed to write simoutput.dot: " + e.getMessage());
        }
    }
}
