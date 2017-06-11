
import org.springframework.boot.loader.LaunchedURLClassLoader;
import org.springframework.boot.loader.Launcher;
import org.springframework.boot.loader.archive.Archive;
import org.springframework.boot.loader.archive.ExplodedArchive;
import org.springframework.boot.loader.archive.JarFileArchive;

import java.io.File;
import java.net.URI;
import java.net.URL;
import java.security.CodeSource;
import java.security.ProtectionDomain;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by dengrong on 2017/6/6.
 */
public class DependArchiveLauncher extends Launcher {
    private Archive archive;
    @Override
    protected String getMainClass() throws Exception {
        return null;
    }

    @Override
    protected List<Archive> getClassPathArchives() throws Exception {
        ArrayList archives = new ArrayList(this.archive.getNestedArchives(new Archive.EntryFilter() {
            public boolean matches(Archive.Entry entry) {
                return DependArchiveLauncher.this.isNestedArchive(entry);
            }
        }));
        this.postProcessClassPathArchives(archives);
        return archives;
    }
    protected boolean isNestedArchive(Archive.Entry entry) {
        return entry.isDirectory()?entry.getName().equals("BOOT-INF/classes/"):entry.getName().startsWith("BOOT-INF/lib/");
    }
    protected void postProcessClassPathArchives(List<Archive> archives) throws Exception {
    }
    public ClassLoader getClassLoader(String path) throws Exception{
        this.archive = this.createMyArchive(path);
        ClassLoader classLoader = this.createClassLoader(this.getClassPathArchives());
        return classLoader;
    }
    protected ClassLoader createClassLoader(List<Archive> archives) throws Exception {
        ArrayList urls = new ArrayList(archives.size());
        Iterator var3 = archives.iterator();

        while(var3.hasNext()) {
            Archive archive = (Archive)var3.next();
            urls.add(archive.getUrl());
        }

        return this.createClassLoader((URL[])urls.toArray(new URL[urls.size()]));
    }

    protected ClassLoader createClassLoader(URL[] urls) throws Exception {
        return new LaunchedURLClassLoader(urls, null);
    }
    private Archive createMyArchive(String path) throws Exception{
//        ProtectionDomain protectionDomain = Launcher.class.getProtectionDomain();
//        CodeSource codeSource = protectionDomain.getCodeSource();
//        URI location = codeSource == null?null:codeSource.getLocation().toURI();
//        String path = location == null?null:location.getSchemeSpecificPart();
        if(path == null) {
            throw new IllegalStateException("Unable to determine code source archive");
        } else {
            File root = new File(path);
            if(!root.exists()) {
                throw new IllegalStateException("Unable to determine code source archive from " + root);
            } else {
                return (Archive)(root.isDirectory()?new ExplodedArchive(root):new JarFileArchive(root));
            }
        }
    }
}
