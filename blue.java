public class RFCommServer extends Thread{

//based on java.util.UUID
private static UUID MY_UUID = UUID.fromString("446118f0-8b1e-11e2-9e96-0800200c9a66");

// The local server socket
private BluetoothServerSocket mmServerSocket;

// based on android.bluetooth.BluetoothAdapter
private BluetoothAdapter mAdapter;
private BluetoothDevice remoteDevice;

private Activity activity;

public RFCommServer(Activity activity) {
    this.activity = activity;
}

public void run() {
    BluetoothSocket socket = null;
    mAdapter = BluetoothAdapter.getDefaultAdapter();        

    // Listen to the server socket if we're not connected
    while (true) {

        try {
            // Create a new listening server socket
            Log.d(this.getName(), ".....Initializing RFCOMM SERVER....");

            // MY_UUID is the UUID you want to use for communication
            mmServerSocket = mAdapter.listenUsingRfcommWithServiceRecord("MyService", MY_UUID);
            //mmServerSocket = mAdapter.listenUsingInsecureRfcommWithServiceRecord(NAME, MY_UUID); // you can also try using In Secure connection...

            // This is a blocking call and will only return on a
            // successful connection or an exception
            socket = mmServerSocket.accept();

        } catch (Exception e) {

        }

        try {
            Log.d(this.getName(), "Closing Server Socket.....");
            mmServerSocket.close();

            InputStream tmpIn = null;
            OutputStream tmpOut = null;

            // Get the BluetoothSocket input and output streams

            tmpIn = socket.getInputStream();
            tmpOut = socket.getOutputStream();

            DataInputStream mmInStream = new DataInputStream(tmpIn);
            DataOutputStream mmOutStream = new DataOutputStream(tmpOut);

            // here you can use the Input Stream to take the string from the client whoever is connecting
            //similarly use the output stream to send the data to the client

            RelativeLayout layout = (RelativeLayout) activity.findViewById(R.id.relativeLayout_Layout);
            TextView text = (TextView) layout.findViewById(R.id.textView_Text);

            text.setText(mmInStream.toString());
        } catch (Exception e) {
            //catch your exception here
        }
    }
}