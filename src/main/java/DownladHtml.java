import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

// www.baidu.com
// www.alipay.com
// www.taobao.com
// 通过 java，并发的下载这三个域名的html文件，并且把它压缩到一个 zip 里。

public class DownladHtml {
    private static String[] url = {"http://www.baidu.com","http://www.alipay.com","http://www.taobao.com"};
    private static final int BUFFER_SIZE = 2 * 1024;
    private static List<File> srcList = new ArrayList<>();

    /**
     * 下载指定网页HTML
     * @param: url
     * @return
     * @throws
     * */
    public static void zipHtml(String url) {
        FileOutputStream fos = null;
        InputStream is = null;
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        String indexName = url.split("\\.")[1];

        try {
            File dest = new File(indexName+".html");
            fos = new FileOutputStream(dest);

            URL uri = new URL(url);
            is = uri.openStream();

            bis = new BufferedInputStream(is);
            bos = new BufferedOutputStream(fos);

            byte[] bytes = new byte[BUFFER_SIZE];
            int length;
            while ((length = bis.read(bytes, 0, bytes.length)) != -1) {
                fos.write(bytes, 0, length);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                is.close();
                fos.close();
                bis.close();
                bos.close();
            } catch (Exception e) {
                System.out.println("关闭文件流出错");
                e.printStackTrace();
            }
        }
    }

    /**
     * 指定文件压缩成zip
     * @param: srcFiles,out
     * @return
     * @throws RuntimeException
     * */
    public static void toZip(List<File> srcFiles, OutputStream out) throws RuntimeException {
        ZipOutputStream zos = null ;
        try {
            zos = new ZipOutputStream(out);
            for (File srcFile : srcFiles) {
                byte[] buf = new byte[BUFFER_SIZE];
                zos.putNextEntry(new ZipEntry(srcFile.getName()));
                int len;
                FileInputStream in = new FileInputStream(srcFile);
                while ((len = in.read(buf)) != -1) {
                    zos.write(buf, 0, len);
                }
                zos.closeEntry();
                in.close();
            }
        } catch (Exception e) {
            throw new RuntimeException("zip error from ZipUtils",e);
        } finally{
            if (zos != null) {
                try {
                    zos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main (String[] args) {

        // 并发下载
        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        int count = url.length;

        for (int i = 0; i < count; i++) {
            final int index = i;
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    zipHtml(url[index]);
                }
            });
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        threadPool.shutdown();

        // 打包操作
        try{
            for (int i = 0 ; i < count ; i++) {
                String indexName = url[i].split("\\.")[1];
                File dest = new File(indexName+".html");
                srcList.add(dest);
            }
            OutputStream zipfos = new FileOutputStream("index.zip");
            toZip(srcList,zipfos);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}


