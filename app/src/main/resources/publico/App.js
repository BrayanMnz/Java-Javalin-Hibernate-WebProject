
        function manejadorPersistencia(e) {
            console.log("Evento: "+e)
            console.log('key: '+e.key);
            console.log('old: '+ e.oldValue);
            console.log('new: '+ e.newValue);
            console.log('url: '+ e.url);
            console.log('storage: '+e.storageArea);
        }


        window.addEventListener('storage', manejadorPersistencia);

        function init() {
            //Guardando la información en el local.
            var primerAcceso = localStorage.getItem("fechaAcceso");
            if(primerAcceso == null) {
                var tmp = new Date().toUTCString();
                localStorage.setItem("fechaAcceso", tmp); //Siempre guarda
                primerAcceso = tmp;
            }


            //Almacenando información en el ambiente de sesión.
            sessionStorage.setItem("fechaAcceso", new Date().toUTCString());
            
            document.getElementById("ultimaVez").innerText ="La última  vez accedido: "+sessionStorage.getItem("fechaAcceso");
            //Mostrando.
            //document.getElementById("primeraVez").innerText ="La primera vez accedido: "+primerAcceso;
           

        }
