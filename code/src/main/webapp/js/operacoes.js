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

var iCorrenteId;
var IPoupancaId;

var tconfirma = false;

var cpfId;
var emailId;
var usuarioId;

var emailLogado;
var idEmail;

function armazenaEmail(){
	idEmail = document.getElementById("armazenaEmail");
	emailLogado = idEmail.value;
}

function buttonsAcoes(id) {
	debugger;
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
			iCorrenteId = document.getElementById("inputCorrente");
			
			poupancaId = document.getElementById('poupanca');
			iPoupancaId = document.getElementById("inputPoupanca");
			if (!correnteId.classList.contains("activeButton")) {
                if (poupancaId.classList.contains("activeButton")){
                    poupancaId.classList.remove("activeButton");
                    iPoupancaId.removeAttribute("name", "tipoConta");
                }
				
				correnteId.classList.add("activeButton");
				iCorrenteId.setAttribute("name", "tipoConta");
			} else {
				correnteId.classList.remove("activeButton");
				iCorrenteId.removeAttribute("name", "tipoConta");
			}

			break;

		case 'poupanca':
			poupancaId = document.getElementById(id);
			iPoupancaId = document.getElementById("inputPoupanca");
			
			correnteId = document.getElementById('corrente');
			iCorrenteId = document.getElementById("inputCorrente");
			if (!poupancaId.classList.contains("activeButton")) {
                if (correnteId.classList.contains("activeButton")){
                    correnteId.classList.remove("activeButton");
                    iCorrenteId.removeAttribute("name", "tipoConta");
                }

				poupancaId.classList.add("activeButton");
				iPoupancaId.setAttribute("name", "tipoConta");
			} else {
				poupancaId.classList.remove("activeButton");
				iPoupancaId.removeAttribute("name", "tipoConta");
			}

			break;

		case 'email':
			emailId = document.getElementById(id);
			if (!emailId.classList.contains("activeButton")) {
				emailId.classList.add("activeButton");
				temail = true;
			} else {
				emailId.classList.remove("activeButton");
				temail = false;
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