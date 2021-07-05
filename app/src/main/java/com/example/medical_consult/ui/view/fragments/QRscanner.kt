package com.example.medical_consult.ui.view.fragments

import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.edit
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import com.budiyev.android.codescanner.*
import com.example.medical_consult.R
import com.example.medical_consult.data.api.RetrofitService
import com.example.medical_consult.data.model.*
import kotlinx.android.synthetic.main.fragment_profil_medecin.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback
import java.lang.Integer.parseInt

class QRscanner : Fragment() {

     lateinit var codeScanner: CodeScanner
    val  MY_CAMERA_PERMISSION = 1111

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.scannerview, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val scannerView = view.findViewById<CodeScannerView>(R.id.scanner_view)
        val activity = requireActivity()
        codeScanner = CodeScanner(activity, scannerView)
        codeScanner.camera = CodeScanner.CAMERA_BACK
        codeScanner.formats = CodeScanner.ALL_FORMATS
        codeScanner.autoFocusMode = AutoFocusMode.SAFE
        codeScanner.scanMode = ScanMode.SINGLE
        codeScanner.isAutoFocusEnabled = true
        codeScanner.isFlashEnabled = false
        codeScanner.decodeCallback = DecodeCallback {
            activity.runOnUiThread {
                try {
                    var rawQR = it.text.split(":")
                    VerifRdv(rawQR,view)
                }
                catch (e:Exception)
                {
                    Toast.makeText(activity,"Le rendez vous n'est pas valide", Toast.LENGTH_SHORT).show()
                }
            }
        }
        codeScanner.errorCallback = ErrorCallback { // or ErrorCallback.SUPPRESS
            activity.runOnUiThread {
                Toast.makeText(activity, "Camera initialization error: ${it.message}",
                    Toast.LENGTH_LONG).show()
            }
        }

        scannerView.setOnClickListener {
            codeScanner.startPreview()
        }

    }

    fun VerifRdv(rawQR:List<String>,view: View){
        var id = parseInt(rawQR[0])
        var potentialRdv = Rdv(id,parseInt(rawQR[1]), parseInt(rawQR[2]),
            parseInt(rawQR[3]),rawQR[4],rawQR[5])
        var plage =  getPlageHorraire(parseInt(rawQR[1]))
        //apicall
        var call = RetrofitService.endpoint.verifRdv(id,potentialRdv)
        call.enqueue( object : Callback<verifResponse> {
            override fun onResponse(call: Call<verifResponse>?, response: Response<verifResponse>?) {
                if (response?.isSuccessful!!) {
                    val data: verifResponse? = response.body()
                    if (data != null ) {
                        if(data.result){
                            val bundle = bundleOf("plageHorraireId" to plage,"medecinId" to rawQR[2],"patientId" to getNomPatient(
                                parseInt(rawQR[3])),"state" to rawQR[4],"date" to rawQR[5] )
                            view.findNavController().navigate(R.id.action_QRscanner_to_rdvVerif , bundle)
                        }
                        else{
                            Toast.makeText(activity,"Le rendez vous n'est pas valide", Toast.LENGTH_SHORT).show()
                        }
                    }
                } else {
                    Toast.makeText(activity, "Les entrees sont incorrectes", Toast.LENGTH_LONG).show()
                }
            }
            override fun onFailure(call: Call<verifResponse>?, t: Throwable?) {
                Toast.makeText(activity,"L'utilisateur n'existe pas", Toast.LENGTH_SHORT).show()
            }
        })
    }


    fun getPlageHorraire(id:Int):String {
        var call = RetrofitService.endpoint.getPlageHorraire(id)
        var ret = ""
        try {
            call.enqueue( object : Callback<PlageHorraire> {
                override fun onResponse(call: Call<PlageHorraire>?, response: Response<PlageHorraire>?) {
                    if (response?.isSuccessful!!) {
                        val data: PlageHorraire? = response.body()
                        if (data != null ) {
                            ret =  "${data.heureDebut}H - ${data.heureFin}H"
                            Toast.makeText(activity, ret, Toast.LENGTH_LONG).show()
                        }
                    } else {
                        Toast.makeText(activity, "Les entrees sont incorrectes", Toast.LENGTH_LONG).show()
                    }
                }
                override fun onFailure(call: Call<PlageHorraire>?, t: Throwable?) {
                    Toast.makeText(activity,"plage horraire n'existant pas", Toast.LENGTH_SHORT).show()
                }
            })
        }catch (e:Exception){
            Toast.makeText(activity,"plage horraire n'existant pas", Toast.LENGTH_SHORT).show()
        }
        return ret
    }

    fun getNomPatient(id:Int):String {
        var call = RetrofitService.endpoint.getPatient(id)
        var ret = ""
        try {
            call.enqueue( object : Callback<Patient> {
                override fun onResponse(call: Call<Patient>?, response: Response<Patient>?) {
                    if (response?.isSuccessful!!) {
                        val data: Patient? = response.body()
                        if (data != null ) {
                            ret = data.fullName
                        }
                    } else {
                        Toast.makeText(activity, "Les entrees sont incorrectes", Toast.LENGTH_LONG).show()
                    }
                }
                override fun onFailure(call: Call<Patient>?, t: Throwable?) {
                    Toast.makeText(activity,"plage horraire n'existant pas", Toast.LENGTH_SHORT).show()
                }
            })
        }catch (e:Exception){
            Toast.makeText(activity,"plage horraire n'existant pas", Toast.LENGTH_SHORT).show()
        }
        return ret
    }

    override fun onResume() {
        super.onResume()
        codeScanner.startPreview()
    }

    override fun onPause() {
        codeScanner.releaseResources()
        super.onPause()
    }

}