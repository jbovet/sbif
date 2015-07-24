# sbif
Cliente Java/Groovy para la API de la Superintendencia de Bancos e Instituciones Financieras de Chile 


[![Build Status](https://travis-ci.org/jbovet/sbif.svg?branch=master)](https://travis-ci.org/jbovet/sbif)


Prerequisites:

	groovy-all:2.4.3
	groovy-wslite:1.1.2

Installation:

 [ ![Download](https://api.bintray.com/packages/josebovet/generic/sbif/images/download.svg) ](https://bintray.com/josebovet/generic/sbif/_latestVersion)


Usage:

    SBIFTemplate sbifTemplate;

    def apiKey = "API_KEY"
    sbifTemplate = new SBIFTemplate(apiKey);

    IPCOperations ipcOPerarions = sbifTemplate.getIpcOperations()

    ...

 SBIF Operations:

     DolarOperations
     TMCOperations
     IPCOperations
     UTMOperations
     EuroOperations

Contribute
;)


