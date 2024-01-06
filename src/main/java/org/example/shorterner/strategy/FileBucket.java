package org.example.shorterner.strategy;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * @author Denis Zolotarev
 */
public class FileBucket {
    private Path path;

    public FileBucket() {
        try {
            path = Files.createTempFile(Integer.toHexString(hashCode()), ".tmp");
            path.toFile().deleteOnExit();

            Files.deleteIfExists(path);
            Files.createFile(path);
        } catch (IOException ignored) {

        }
    }

    public long getFileSize() {
        try {
            return Files.size(path);
        } catch (IOException ignored) {

        }
        return 0;
    }

    public void putEntry(Entry entry) {
        try (ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(path))) {
            oos.writeObject(entry);
        } catch (Exception ignored) {

        }
    }

    public Entry getEntry() {
        if (getFileSize() > 0) {
            try (ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(path))) {
                return (Entry) ois.readObject();
            } catch (Exception ignored) {

            }
        }
        return null;
    }

    public void remove() {
        try {
            Files.delete(path);
        } catch (IOException ignored) {

        }
    }
}
