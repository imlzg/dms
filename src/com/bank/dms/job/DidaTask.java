package com.bank.dms.job;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Formatter;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.struts2.ServletActionContext;

import com.bank.dms.dao.imp.UncommittedEmpDAOImp;
import com.bank.dms.job.Mail;
import com.bank.dms.utils.PDFUtil;

public class DidaTask {
	private Mail mail;
	private String rtxURL;
	private String pdfURI;

	public Mail getMail() {
		return mail;
	}
	public void setMail(Mail mail) {
		this.mail = mail;
	}
	public String getRtxURL() {
		return rtxURL;
	}
	public void setRtxURL(String rtxURL) {
		this.rtxURL = rtxURL;
	}

	public String getPdfURI() {
		return pdfURI;
	}
	public void setPdfURI(String pdfURI) {
		this.pdfURI = pdfURI;
	}

	public void work() {
		System.out.println("di da di.....");

//		try {
//	        List<String> names = UncommittedEmpDAOImp.getUncommittedNames();
//	        for (String name : names) {
//	            if(name != null)
//	                rtx("你好！你还没有提交今天的日报！", name.trim());
//	        }
//		} catch (ClientProtocolException e1) {
//			e1.printStackTrace();
//		} catch (IOException e1) {
//			e1.printStackTrace();
//		}

		List<String[]> list = UncommittedEmpDAOImp.pdfContent();
		if(list != null){
		    for (Object[] objects : list) {
                String[] table_right_content = new String[]{objects[0].toString(), objects[1].toString(), objects[2].toString(), objects[3].toString()};

                String pdfName = "佰瑞日报汇总-"+ objects[0].toString() + ".pdf";
                String pdfPath = pdfURI + pdfName;
                PDFUtil.createPDF(pdfPath, table_right_content, UncommittedEmpDAOImp.dailyContent(objects[4]));

                File attach = new File(pdfPath);
                Map<String, String> map = UncommittedEmpDAOImp.getManagerEmails(objects[4]);
                if(map != null){
                    for (String key : map.keySet()) {
                        //String[] ss = {key};
                        String[] ss = {"admin.lehehe@126.com"};
/*                        try {
                            mail.send("bank2@126.com", ss, "佰瑞日报汇总", map.get(key), pdfName, attach);
                        } catch (MessagingException e) {
                            e.printStackTrace();
                        } catch (UnsupportedEncodingException e) {
							e.printStackTrace();
						}*/
                        break;
                    }
                }

            }
		}
	}
	
	public void sendEmailTOUncommitted() {
        System.out.println("sendEmailTOUncommitted");

        List<String> emails = UncommittedEmpDAOImp.getUncommittedNames();
        if(emails != null && emails.size() > 0){
            //String[] arr = (String[]) emails.toArray(new String[emails.size()]);
            String[] arr = {"admin.lehehe@126.com"};
            try {
                mail.send("bank_dms@126.com", arr, "佰瑞日报系统自动提示", "您好，您今日还没有提交日报！");
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }
    }
	
	/*
	 * msg: the message
	 * receiver: the employee's name
	 */
	public void rtx(String msg, String receiver) throws ClientProtocolException, IOException {
		String u = new Formatter().format(rtxURL, URLEncoder.encode(msg, "GB2312"), URLEncoder.encode(receiver, "GB2312")).toString();
		CloseableHttpClient client = HttpClients.createDefault();
		HttpPost p = new HttpPost(u);
		CloseableHttpResponse resp = client.execute(p);
		HttpEntity respEntity = resp.getEntity();

		System.out.println(EntityUtils.toString(respEntity));

		resp.close();
		client.close();
		
	}
	
}
