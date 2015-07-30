# sbif
Cliente Java/Groovy para la API de la Superintendencia de Bancos e Instituciones Financieras de Chile 


[![Build Status](https://travis-ci.org/jbovet/sbif.svg?branch=master)](https://travis-ci.org/jbovet/sbif)


[![Coverage Status](https://coveralls.io/repos/jbovet/sbif/badge.svg?branch=master&service=github)](https://coveralls.io/github/jbovet/sbif?branch=master)

Prerequisites:

	groovy-all:2.4.4
	groovy-wslite:1.1.2

Installation:

 [ ![Download](https://api.bintray.com/packages/josebovet/generic/sbif/images/download.svg) ](https://bintray.com/josebovet/generic/sbif/_latestVersion)


Usage:

    SBIFTemplate sbifTemplate;

    def apiKey = "API_KEY"
    sbifTemplate = new SBIFTemplate(apiKey);

    IPCOperations ipcOPerations = sbifTemplate.getIpcOperations()
    IPC ipc = ipcOPerations.getIPC()
    print ipc.valor
    print ipc.fecha
    ...

 SBIF Operations:

     DolarOperations
     TMCOperations
     IPCOperations
     UTMOperations
     EuroOperations
     UFOperations

Contribute
;)


