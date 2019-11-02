import com.client.ipLocation.IpToLocation;
import com.client.ipLocation.ipToLocationGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class grpcClient {
    public static void main(String[] args ){
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 9090).usePlaintext().build();

        // stubs needed to call particular server rpc/api which are generated from proto- files created in grpc server
        ipToLocationGrpc.ipToLocationBlockingStub ipLocationClientStub = ipToLocationGrpc.newBlockingStub(channel);

        IpToLocation.IpLocationRequest request= IpToLocation.IpLocationRequest.newBuilder().setIp("103.5.150.3").build();
        IpToLocation.IpLocationResponse response= ipLocationClientStub.getMyLocationFromIp(request);

        System.out.println("Response from grpcServer: \n" +response);
    }

}
