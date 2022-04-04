package id.ac.uinsgd.mybindingList.mybindingList.service;

import id.ac.uinsgd.mybindingList.mybindingList.employee.Employee;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;

@Service
public class EmployeeService {
    public void save(Employee employee) {
        saveFile(employee.getDocument());
        // save other employee data ceunah
    }

    public void saveFile(MultipartFile multipartFile) {
        try {
            saveToFileSystem(multipartFile);
        } catch (Exception e){
            throw new RuntimeException("Unable to save file", e);
        }
    }

    public static void saveToFileSystem (MultipartFile multipartFile) throws IOException {
        String dir = Files.createTempDirectory("tmpDir").toFile().getAbsolutePath();
        File file = new File(dir + File.pathSeparator + multipartFile.getName());

        try (OutputStream os = new FileOutputStream(file)){
            os.write(multipartFile.getBytes());
        }
    }
}
