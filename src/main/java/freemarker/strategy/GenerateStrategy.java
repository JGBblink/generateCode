package freemarker.strategy;

import freemarker.exection.NoCreateEntityException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.util.FreeMarkerTemplateUtils;
import freemarker.util.FreemarkerStrUtils;

import java.io.*;
import java.util.Map;

/**
 * 自动代码生成策略接口
 *
 * @author JinGuiBo
 * @date 2018/7/4.
 */
public abstract class GenerateStrategy {
    protected Class clazz;
    protected Map<String, String> parame;

    protected boolean isCreated;

    /**
     * 生成代码
     */
    public abstract void configParame() throws Exception;

    public void generateCode() {

        try {
            configParame();
            isCreated = true;
        }catch (NoCreateEntityException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    protected void executeTemple(String templateName,String targetPath,Object templeParam,String charSet,int bufferSize) {

        // 设置模板信息,并产生模板代码
        Template template;
        try {
            template = FreeMarkerTemplateUtils.getTemplate(templateName);
            String dir = FreemarkerStrUtils.findDir(targetPath,"/");

            if(new File(targetPath).exists()) {
                System.out.println(targetPath + " is exist");
                return;
            }

            File file = new File(dir);
            if(!file.exists()) {
                System.out.println(file.getAbsolutePath());
                file.mkdirs();
            }

            FileOutputStream fos = new FileOutputStream(targetPath);
            Writer out = new BufferedWriter(new OutputStreamWriter(fos, charSet), bufferSize);
            template.process(templeParam, out);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }

    public boolean isCreated() {
        return isCreated;
    }
}
