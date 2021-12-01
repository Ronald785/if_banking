var dpix = false;
var dboleto = false;
var dconfirma = false;

var tcpf = false;
var temail = false;
var tusuario = false;

var boletoId;
var pixId;

var sconfirma = false;

var correnteId;
var poupancaId;

var tconfirma = false;

var cpfId;
var emailId;
var usuarioId;

function buttonsAcoes(id) {

    switch (id) {
        
        case 'pix':
            pixId = document.getElementById(id);
            boletoId = document.getElementById('boleto');
            if (!pixId.classList.contains("activeButton")) {
                boletoId.classList.remove("activeButton");
                pixId.classList.add("activeButton");
                dpix = true;
            } else {
                pixId.classList.remove("activeButton");
                dpix = false;
            }

            break;

        case 'boleto':
            boletoId = document.getElementById(id);
            pixId = document.getElementById('pix');
            if (!boletoId.classList.contains("activeButton")) {
                pixId.classList.remove("activeButton");
                boletoId.classList.add("activeButton");
                dboleto = true;
            } else {
                boletoId.classList.remove("activeButton");
                dboleto = false;
            }

            break;

        case 'corrente':
            correnteId = document.getElementById(id);
            poupancaId = document.getElementById('poupanca');
            if (!correnteId.classList.contains("activeButton")) {
                poupancaId.classList.remove("activeButton");
                correnteId.classList.add("activeButton");
            } else {
                correnteId.classList.remove("activeButton");
            }

            break;

        case 'poupanca':
            poupancaId = document.getElementById(id);
            correnteId = document.getElementById('corrente');
            if (!poupancaId.classList.contains("activeButton")) {
                correnteId.classList.remove("activeButton");
                poupancaId.classList.add("activeButton");
            } else {
                poupancaId.classList.remove("activeButton");
            }

            break;

        case 'cpf':
            cpfId = document.getElementById(id);
            emailId = document.getElementById('email');
            usuarioId = document.getElementById('usuario');
            if (!cpfId.classList.contains("activeButton")) {
                emailId.classList.remove("activeButton");
                usuarioId.classList.remove("activeButton");
                cpfId.classList.add("activeButton");
                tcpf = true;
            } else {
                cpfId.classList.remove("activeButton");
                tcpf = false;
            }

            break;

        case 'email':
            emailId = document.getElementById(id);
            cpfId = document.getElementById('cpf');
            usuarioId = document.getElementById('usuario');
            if (!emailId.classList.contains("activeButton")) {
                cpfId.classList.remove("activeButton");
                usuarioId.classList.remove("activeButton");
                emailId.classList.add("activeButton");
                temail = true;
            } else {
                emailId.classList.remove("activeButton");
                temail = false;
            }

            break;

        case 'usuario':
            usuarioId = document.getElementById(id);
            cpfId = document.getElementById('cpf');
            emailId = document.getElementById('email');
            if (!usuarioId.classList.contains("activeButton")) {
                emailId.classList.remove("activeButton");
                cpfId.classList.remove("activeButton");
                usuarioId.classList.add("activeButton");
                tusuario = true;
            } else {
                usuarioId.classList.remove("activeButton");
                tusuario = false;
            }

            break;

        
    }
}

function somenteNumeros(num) {
    var er = /[^0-9. ,-,]/;
    er.lastIndex = 0;
    var campo = num;
    if (er.test(campo.value)) {
        campo.value = "";
    }
}