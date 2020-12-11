# Solicitudes HTTP Con Volley
Volley es una biblioteca HTTP que facilita y agiliza el uso de redes en apps para Android. Los beneficios son amplios, pero nos interesa su compatibilidad con strings sin procesar, imágenes y JSON.  

### Incorporar Volley a tu proyecto android
Busca en la carpeta Gradle Scripts, el fichero `build.gradle`, dentro de este fichero agrega la siguiente línea en el apartado `dependencies`:
```kotlin
    implementation 'com.android.volley:volley:1.1.1'
```  

### Agregar el permiso de INTERNET
Es necesario agregar el permiso [android.permission.INTERNET](https://developer.android.com/reference/android/Manifest.permission?hl=es#INTERNET) al manifiesto de tu aplicación. Si no se establece este permiso, tu aplicación no podrá conectarse a internet.  

### Iniciar una cola de solicitudes usando el método newRequestQueue
Volley proporciona el método `Volley.newRequestQueue` que te permite iniciar una cola de solicitudes por tí. Ejemplo:
```kotlin
    val url = "https://api.github.com/users/yessermiranda13"
    val queue = Volley.newRequestQueue(this)
    val solicitud = StringRequest(Request.Method.GET,url,Response.Listener<String> {
        response ->
        try {
            Log.d("ResultadoVolley",response)
        } catch (e:Exception){
            Log.e("ErrorVolley",e.message)
        }
    }, Response.ErrorListener {
            Log.e("ErrorVolley","ERROR EN LA CONSULTA")
    })
    queue.add(solicitud)
```
El resultado será registro en debug por si obtiene los datos o un registro de error por si se lanza una excepción