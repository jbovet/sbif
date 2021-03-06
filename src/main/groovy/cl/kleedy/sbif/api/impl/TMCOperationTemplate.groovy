package cl.kleedy.sbif.api.impl

import cl.kleedy.sbif.api.BasicOperations
import cl.kleedy.sbif.api.SBIFClientException
import cl.kleedy.sbif.api.TMCOperations
import cl.kleedy.sbif.api.indicadores.TMC
import wslite.rest.RESTClient

/**
 * Operaciones Tasa Maxima Convencional
 * Created by josebovet on 7/14/15.
 */
class TMCOperationTemplate extends BasicOperations implements TMCOperations {


    TMCOperationTemplate(RESTClient restClient, String apiKey) {
        super(restClient, apiKey)
    }

    @Override
    List<TMC> getTMCByYear(int year) throws SBIFClientException {
        Map params = ['year': year]
        return getResource(params, 'tmc', 'getTCM')
    }

    @Override
    List<TMC> getTMCByYearAndMonth(int year, int month) throws SBIFClientException {
        Map params = ['year': year, 'month': month]
        return getResource(params, 'tmc', 'getTCM')
    }

    @Override
    List<TMC> getTMCByPreviousYearAndMonth(int year, int month) throws SBIFClientException {
        Map params = ['year': year, 'month': month]
        return getResource(params, 'tmc', '/anteriores', 'getTCM')
    }

    @Override
    List<TMC> getTMCByLaterYearAndMonth(int year, int month) throws SBIFClientException {
        Map params = ['year': year, 'month': month]
        return getResource(params, 'tmc', '/posteriores', 'getTCM')
    }

    @Override
    List<TMC> getTMCByPeriodYearAndMonth(int year, int month, int year2, int month2) throws SBIFClientException {
        Map params = ['year': year, 'month': month, 'year2': year2, 'month2': month2]
        return getResource(params, 'tmc', '/periodo', 'getTCM')
    }

    /***
     * Llama al recurso TMC,parseando la respuesta en formato json
     * @param url
     * @return List <TMC>
     */
    private List<TMC> getTCM(String url) throws SBIFClientException {
        def tcms = call(url).json.TMCs
        def list = []
        tcms.each() { data ->
            list << new TMC(titulo: data.Titulo, subTitulo: data.SubTitulo,
                    valor: (data.Valor != null && data.Valor.trim().length() > 0) ? Double.parseDouble(data.Valor.trim().replaceAll(",", '.')) : 0,
                    fecha: Date.parse('yyyy-MM-dd', data.Fecha),
                    tipo: (data.Tipo != null && data.Tipo.trim().length() > 0) ? Integer.parseInt(data.Tipo.trim().replaceAll(",", '.')) : 0
            )
        }
        return list
    }

}
