package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;




import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportManager implements ITestListener {

	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;

	String repName;

	@Override
	public void onTestStart(ITestResult result) {
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups()); // to display groups in report
		test.log(Status.PASS, result.getName() + " got successfully executed");
	}

	@Override
	public void onTestSuccess(ITestResult result) {

	}

	@Override
	public void onTestFailure(ITestResult result) {
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());

		test.log(Status.FAIL, result.getName() + " got failed");
		test.log(Status.INFO, result.getThrowable().getMessage());

		
		try {
			String imgPath = new BaseClass().captureScreen(result.getName());
			test.addScreenCaptureFromPath(imgPath);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void onTestSkipped(ITestResult result) {
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, result.getName() + " got skipped");
		test.log(Status.INFO, result.getThrowable().getMessage());

	}

	@Override
	public void onStart(ITestContext context) {
		/*
		 * SimpleDateFormat df=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss"); Date dt=new
		 * Date(); String currentdatetimestamp=df.format(dt);
		 */

		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());// time stamp
		repName = "Test-Report-" + timeStamp + ".html";
		sparkReporter = new ExtentSparkReporter(".\\reports\\" + repName);// specify location of the report

		sparkReporter.config().setDocumentTitle("opencart Automation Report"); // Title of report
		sparkReporter.config().setReportName("opencart Functional Testing"); // name of the report
		sparkReporter.config().setTheme(Theme.DARK);

		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Application", "opencart");
		extent.setSystemInfo("Module", "Admin");
		extent.setSystemInfo("Sub Module", "Customers");
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		extent.setSystemInfo("Environemnt", "QA");

		String os = context.getCurrentXmlTest().getParameter("os");
		extent.setSystemInfo("Operating System", os);

		String browser = context.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("Browser", browser);

		List<String> includedGroups = context.getCurrentXmlTest().getIncludedGroups();
		if (!includedGroups.isEmpty()) {
			extent.setSystemInfo("Groups", includedGroups.toString());
		}
	}

	@Override
	public void onFinish(ITestContext context) {
		extent.flush();

		String pathOfExtentReport = System.getProperty("user.dir") + "\\reports\\" + repName;
		File extentReport = new File(pathOfExtentReport);

		try {
			Desktop.getDesktop().browse(extentReport.toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}
		//To send email with attachment
				//sendEmail(sender email,sender password(encrypted),recipient email);
		/*
		 * sendEmail("prashantkamble271991@gmail.com","xyz123xyz",
		 * "prashantkamble271991@gmail.com"); }
		 * 
		 * 
		 * //User defined method for sending email.. public void sendEmail(String
		 * senderEmail,String senderPassword,String recipientEmail) { // SMTP server
		 * properties Properties properties = new Properties();
		 * properties.put("mail.smtp.auth", "true");
		 * properties.put("mail.smtp.starttls.enable", "true");
		 * properties.put("mail.smtp.host", "smtp.gmail.com");
		 * properties.put("mail.smtp.port", "587");
		 * 
		 * // Create a Session object Session session = Session.getInstance(properties,
		 * new Authenticator() { protected PasswordAuthentication
		 * getPasswordAuthentication() { return new PasswordAuthentication(senderEmail,
		 * senderPassword); } });
		 * 
		 * try { // Create a MimeMessage object Message message = new
		 * MimeMessage(session);
		 * 
		 * // Set the sender and recipient addresses message.setFrom(new
		 * InternetAddress(senderEmail)); message.setRecipient(Message.RecipientType.TO,
		 * new InternetAddress(recipientEmail));
		 * 
		 * // Set the subject message.setSubject("Test Report with attachment");
		 * 
		 * // Create a MimeMultipart object Multipart multipart = new MimeMultipart();
		 * 
		 * // Attach the file String filePath = ".\\reports\\"+repName; String fileName
		 * = repName;
		 * 
		 * MimeBodyPart attachmentPart = new MimeBodyPart();
		 * attachmentPart.attachFile(filePath); attachmentPart.setFileName(fileName);
		 * 
		 * // Create a MimeBodyPart for the text content MimeBodyPart textPart = new
		 * MimeBodyPart(); textPart.setText("Please find the attached file.");
		 * 
		 * // Add the parts to the multipart multipart.addBodyPart(textPart);
		 * multipart.addBodyPart(attachmentPart);
		 * 
		 * // Set the content of the message message.setContent(multipart);
		 * 
		 * // Send the message Transport.send(message);
		 * 
		 * System.out.println("Email sent successfully!");
		 * 
		 * } catch (Exception e) { e.printStackTrace(); }
		 */

		/*
		 * try { URL url = new
		 * URL("file:///"+System.getProperty("user.dir")+"\\reports\\"+repName);
		 * 
		 * // Create the email message ImageHtmlEmail email = new ImageHtmlEmail();
		 * email.setDataSourceResolver(new DataSourceUrlResolver(url));
		 * email.setHostName("smtp.googlemail.com"); email.setSmtpPort(465);
		 * email.setAuthenticator(new
		 * DefaultAuthenticator("pavanoltraining@gmail.com","password"));
		 * email.setSSLOnConnect(true); email.setFrom("pavanoltraining@gmail.com");
		 * //Sender email.setSubject("Test Results");
		 * email.setMsg("Please find Attached Report....");
		 * email.addTo("pavankumar.busyqa@gmail.com"); //Receiver email.attach(url,
		 * "extent report", "please check report..."); email.send(); // send the email }
		 * catch(Exception e) { e.printStackTrace(); }
		 */
	}

}
