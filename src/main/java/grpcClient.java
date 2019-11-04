import com.grpc.Email.SendEmail;
import com.grpc.Email.grpcEmailGrpc;
import com.grpc.SMS.SendSMS;
import com.grpc.SMS.grpcSMSGrpc;
import com.grpc.iplocation.IpToLocation;
import com.grpc.iplocation.ipToLocationGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class grpcClient {
    public static void main(String[] args ){
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 9090).usePlaintext().build();

        // stubs needed to call particular server rpc/api which are generated from proto- files created in grpc server
        ipToLocationGrpc.ipToLocationBlockingStub ipLocationClientStub = ipToLocationGrpc.newBlockingStub(channel);
        IpToLocation.IpLocationRequest request= IpToLocation.IpLocationRequest.newBuilder().setIp("103.5.150.3").build();
        IpToLocation.IpLocationResponse response= ipLocationClientStub.getMyLocationFromIp(request);
        System.out.println("Ip to Location response: \n" +response);

        grpcSMSGrpc.grpcSMSBlockingStub smsBlockingStub= grpcSMSGrpc.newBlockingStub(channel);
        SendSMS.SmsRequest smsRequest = SendSMS.SmsRequest.newBuilder().setReceiverNumber("+9779804183965").setMessage("Hey There WHat Up ?").build();
        SendSMS.SmsResponse smsResponse = smsBlockingStub.sendSMS(smsRequest);
        System.out.println("Send SMS response: " + smsResponse);

        grpcEmailGrpc.grpcEmailBlockingStub emailBlockingStub = grpcEmailGrpc.newBlockingStub(channel);
        SendEmail.EmailRequest emailRequest = SendEmail.EmailRequest.newBuilder().setTo("072bct537.shristi@pcampus.edu.np").setSubject("This is Us !").setMessage("Hola How u doing?").setFilePath("E:/Entertainment/Pictures/nagarkot/IMG_6140.JPG").build();
        SendEmail.EmailResponse emailResponse = emailBlockingStub.sendEmail(emailRequest);
        System.out.println("Send Email response: " + emailResponse);

    }

}
