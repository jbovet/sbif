package cl.kleedy.sbif.api.impl

import cl.kleedy.sbif.api.BasicOperations
import cl.kleedy.sbif.api.SBIFClientException
import cl.kleedy.sbif.api.UFOperations
import cl.kleedy.sbif.api.indicadores.UF
import wslite.rest.RESTClient

/**
 * Implemetacion operaciones UF
 * Created by josebovet on 7/30/15.
 */
class UFOperationTemplate extends BasicOperations implements  UFOperations {

    UFOperationTemplate(RESTClient restClient, String apiKey) {
        super(restClient, apiKey)
    }

    @Override
    UF getUF() throws SBIFClientException {
        return getResource([:], 'uf', 'getUFs').first()
    }

    @Override
    List<UF> getUFByYear(int year) throws SBIFClientException {
        return getUFByDate(year,0,0)
    }

    @Override
    List<UF> getUFByYear(int year, int month) throws SBIFClientException {
        return getUFByDate(year, month,0)
    }

    @Override
    UF getUFByYear(int year, int month, int day) throws SBIFClientException {
        return getUFByDate(year,month,day).first()
    }

    private List<UF> getUFByDate(int year, int month, int day){
        def params = ['year': year]
        if (month) params.month = month
        if (day) params.day = day
        return getResource(params, 'uf', 'getUFs')
    }

    @Override
    List<UF> getUFLaterYear(int year) throws SBIFClientException {
        return getUFLaterYear(year,0)
    }

    @Override
    List<UF> getUFLaterYear(int year, int month) throws SBIFClientException {
        return getUFLaterByDate(year, month,0)
    }

    @Override
    UF getUFLaterYear(int year, int month, int day) throws SBIFClientException {
        return getUFLaterByDate(year, month, day).first()
    }

    private List<UF> getUFLaterByDate(int year, int month, int day) throws SBIFClientException {
        def params = ['year': year]
        if (month) params.month = month
        if (day) params.day = day
        return getResource(params, 'uf','/posteriores','getUFs')
    }

    /***
     * Llama al recurso UF, parseando la respuesta en formato json
     * @param url
     * @return List<UF>
     */
    private List<UF> getUFs(String url) throws SBIFClientException {
        def ufList = call(url).json.UFs
        def list = []
        ufList.each() { data ->
            def v = data.Valor.trim()
            if (v.any { it == ',' }) {
                v = v.substring(0, v.indexOf(','))
            }
            //TODO revisar tipo de mascara para Double
            v = (v != null && v.length() > 0) ? Double.parseDouble(v.replace(".", '')) : 0
            list << new UF(valor: v, fecha: Date.parse('yyyy-MM-dd', data.Fecha))
        }
        return list
    }
}
