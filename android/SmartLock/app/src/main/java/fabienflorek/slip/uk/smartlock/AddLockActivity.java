package fabienflorek.slip.uk.smartlock;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddLockActivity extends AppCompatActivity {

    @Bind(R.id.button_scan_qrcode)
    Button buttonScanQR;
    @Bind(R.id.edit_text_lockid)
    EditText editTextLockId;
    @Bind(R.id.edit_text_lockname)
    EditText editTextLockName;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_lock);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.button_scan_qrcode)
    public void scanQRButtonClick() {
        //QR scanner

        Intent intent = new Intent(this,QrScannerActivity.class);
        //code for given activity, helps to know which activity it is when it returns
        startActivityForResult(intent, 1);

    }
    @OnClick(R.id.button_confirmadd)
    public void confirmAddLockClick() {
        //if the forms aren't filled out properly don't send anything back
        if (editTextLockName.getText().toString()=="" || editTextLockId.getText().toString()=="" )
            finish();

        Intent returnIntent = new Intent();
        returnIntent.putExtra("name",editTextLockName.getText().toString());
        returnIntent.putExtra("id",editTextLockId.getText().toString());
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        String result = "";

        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                result=data.getStringExtra("result");
            }
            if (resultCode == Activity.RESULT_CANCELED) {
            }
        }
        editTextLockId.setText(result);
    }


}