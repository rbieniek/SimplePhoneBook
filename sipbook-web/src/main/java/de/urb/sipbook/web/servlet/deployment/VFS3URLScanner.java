/**
 * 
 */
package de.urb.sipbook.web.servlet.deployment;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Set;

import org.jboss.weld.environment.servlet.deployment.URLScanner;
import org.jboss.vfs.VFS;
import org.jboss.vfs.VirtualFile;
import org.jboss.vfs.VirtualFileVisitor;
import org.jboss.vfs.VisitorAttributes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author rainer
 *
 */
public class VFS3URLScanner extends URLScanner {
    private static final Logger log = LoggerFactory.getLogger(VFS3URLScanner.class);

	public VFS3URLScanner(ClassLoader classLoader) {
		super(classLoader);
	}


    @SuppressWarnings("deprecation")
	@Override
    protected void handleArchiveByFile(File file, final Set<String> classes, final Set<URL> urls) throws IOException {
        log.trace("archive: " + file);
        //noinspection deprecation
        handleURL(file.toURL(), classes, urls);
    }

    @Override
    protected void handleURL(URL url, final Set<String> classes, final Set<URL> urls) {
        try {
            final VirtualFile archive = VFS.getChild(url.toURI());
            archive.visit(new VirtualFileVisitor() {
                public VisitorAttributes getAttributes() {
                    return VisitorAttributes.RECURSE_LEAVES_ONLY;
                }

                public void visit(VirtualFile vf) {
                    try {
                        String name = getRelativePath(archive, vf);
                        URL url = vf.toURL();
                        handle(name, url, classes, urls);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        } catch (Exception e) {
            throw new RuntimeException("Error handling url " + url, e);
        }
    }

    /**
     * Get the relative path between two virtual files
     *
     * @param parent the parent
     * @param child  the child
     * @return the relative path
     */
    static String getRelativePath(VirtualFile parent, VirtualFile child) {
        if (child == null)
            throw new IllegalArgumentException("Null child");

        String childPath = child.getPathName();
        if (parent != null) {
            String parentPath = parent.getPathName();

            if (parentPath.length() == childPath.length())
                return "";

            // Not sure about this? It is obviously not a direct child if it is shorter?
            if (parentPath.length() < childPath.length()) {
                if (parentPath.endsWith("/") == false)
                    parentPath = parentPath + "/";
                if (childPath.startsWith(parentPath))
                    return childPath.substring(parentPath.length());
            }
        }

        if (childPath.endsWith("/"))
            childPath = childPath.substring(0, childPath.length() - 1);

        return childPath;
    }
}
