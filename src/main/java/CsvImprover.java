import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class CsvImprover {

    public static void main(String[] args) {

        List<String> uuids = new ArrayList<>();

        try {
            Reader in = new FileReader("input.csv");
            Iterator<CSVRecord> records = CSVFormat.DEFAULT.builder()
                    .setHeader().build().parse(in).iterator();
            while (records.hasNext()) {
                uuids.add(records.next().get(1));
            }

            FileWriter out = new FileWriter("improved.csv");
            CSVPrinter printer = new CSVPrinter(out,
                    CSVFormat.DEFAULT.builder()
                            .setHeader("uuid", "email", "name", "constant").build());

            for (String uuid : uuids) {
                String name = getRandomRoleName();
                printer.printRecord(uuid, getEmail(name), name, "255");
            }

            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    enum Roles {
        DIRECTOR("director"), CANDIDATE("candidate"), RECRUITER("recruiter");
        private final String roleName;

        Roles(String roleName) {
            this.roleName = roleName;
        }
    }

    static String getRandomRoleName() {
        int randNumber = new Random().nextInt(3);
        return Roles.values()[randNumber].roleName + (randNumber + 1);
    }

    static String getEmail(String name) {
        return name + "@sigma-exchtest.sbrf.ru";
    }
}
