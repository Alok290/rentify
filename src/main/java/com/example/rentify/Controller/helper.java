import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@RestController
@RequestMapping("/upload")
public class helper {

    @PostMapping("/help")
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("File is empty");
        }

        try {
            // Determine the file type
            String fileType = Files.probeContentType(Paths.get(file.getOriginalFilename()));

            if (fileType == null) {
                return ResponseEntity.badRequest().body("Could not determine file type");
            }

            // Save file to server
            File tempFile = File.createTempFile("uploaded_", file.getOriginalFilename());
            file.transferTo(tempFile);

            if (fileType.startsWith("video/")) {
                // Handle video processing
                File compressedFile = processVideo(tempFile);
                if (compressedFile != null) {
                    return ResponseEntity.ok("Video uploaded and processed successfully");
                } else {
                    return ResponseEntity.status(500).body("Video processing failed");
                }
            } else if (fileType.startsWith("image/")) {
                // Handle image processing
                processImage(tempFile);
                return ResponseEntity.ok("Image uploaded successfully");
            } else {
                return ResponseEntity.badRequest().body("Unsupported file type");
            }

        } catch (IOException e) {
            return ResponseEntity.status(500).body("Error occurred during file upload");
        }
    }

    private File processVideo(File videoFile) {
        // Prepare the output file
        File outputFile = new File(videoFile.getParent(), "compressed_" + videoFile.getName());

        // FFmpeg command for video compression
        String ffmpegCommand = String.format("ffmpeg -i %s -vcodec libx264 -crf 23 -preset slow %s",
                videoFile.getAbsolutePath(), outputFile.getAbsolutePath());

        try {
            // Run the FFmpeg command
            Process process = Runtime.getRuntime().exec(ffmpegCommand);

            // Wait for the process to complete
            int exitCode = process.waitFor();

            if (exitCode == 0) {
                // Compression successful
                return outputFile;
            } else {
                // Compression failed
                System.err.println("FFmpeg process failed with exit code " + exitCode);
                return null;
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void processImage(File imageFile) {
        // Perform image processing if necessary
        // This is a placeholder; actual implementation will depend on the processing required
    }
}
