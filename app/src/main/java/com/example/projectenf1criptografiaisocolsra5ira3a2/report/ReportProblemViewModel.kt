package com.example.projectenf1criptografiaisocolsra5ira3a2.report

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.apache.commons.net.ftp.FTP
import org.apache.commons.net.ftp.FTPClient
import java.io.File
import java.io.FileInputStream
import java.util.UUID

class ReportProblemViewModel : ViewModel() {
    val user = "if0_40994469"
    val password = "hWnt5Gf6jXqTlP"

    fun enviarProblema(context: Context, problema: String) {

        viewModelScope.launch(Dispatchers.IO) {
            val reportName = "log_${UUID.randomUUID()}.txt"
            val ftpClient = FTPClient()
            var exitosa = false
            var errorMessage = ""

            try {
                context.openFileOutput(reportName, Context.MODE_PRIVATE).use { stream ->
                    stream.write(problema.toByteArray())
                }

                ftpClient.connect("ftpupload.net", 21)

                val loginSuccess = ftpClient.login(user, password)

                if (loginSuccess) {
                    ftpClient.enterLocalPassiveMode()
                    ftpClient.setFileType(FTP.BINARY_FILE_TYPE)


                    if (!ftpClient.changeWorkingDirectory("/htdocs/problems")) {
                        ftpClient.makeDirectory("/htdocs/problems")
                        ftpClient.changeWorkingDirectory("/htdocs/problems")
                    }

                    val archivoLocal = File(context.filesDir, reportName)

                    if (archivoLocal.exists()) {
                        FileInputStream(archivoLocal).use { fileStream ->
                            exitosa = ftpClient.storeFile(reportName, fileStream)
                        }
                    } else {
                        errorMessage = "Archivo local no existe"
                    }

                    ftpClient.logout()
                } else {
                    errorMessage = "El login ha fallat: ${ftpClient.replyString}"
                }

            } catch (e: Exception) {
                exitosa = false
                errorMessage = e.message ?: ""
            } finally {
                if (ftpClient.isConnected) {
                    try { ftpClient.disconnect() } catch (e: Exception) { }
                }
                context.deleteFile(reportName)
            }

            withContext(Dispatchers.Main) {
                if (exitosa) {
                    Toast.makeText(context, "Problema enviat correctament", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "Error: $errorMessage", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}