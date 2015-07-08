/*
 * SBIF Client: Java/Groovy API client implementation for
   Superintendencia de Bancos e Instituciones Financieras de Chile
 * Copyright (C) 2015  Jose P. Bovet Derpich (jose.bovet@gmail.com)
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
package cl.kleedy.sbif

import cl.kleedy.sbif.cl.kleedy.sbif.indicadores.Dolar
import cl.kleedy.sbif.cl.kleedy.sbif.indicadores.TMC
import wslite.http.HTTPClientException
import wslite.rest.RESTClient
import wslite.rest.Response

/**
 * Created by josebovet on 7/1/15.
 * @version 3.0
 */
class SBIFClient {

    static final BASE_URL = "http://api.sbif.cl/api-sbifv3/recursos_api";

    RESTClient restClient

    static apikey

    private SBIFClient() {
    }

    /***
     * Instance for SBIFClient
     * @param apiKey
     */
    static SBIFClient instance(String apiKey) {
        assert apiKey: 'apiKey is required...'
        SBIFClient sbifClient = new SBIFClient();
        sbifClient.apikey = apiKey;
        sbifClient.restClient = new RESTClient(BASE_URL);
        return sbifClient;
    }

    /***
     * Metodo para la llamada a la api
     * @param path
     * @query parameters
     * @return response
     */
    def Response call(path, Map query = [:]) throws SBIFClientException {
        def response
        try {
            query << ['formato': 'json']
            response = restClient.get(path: path, query: query)
        } catch (HTTPClientException hce) {
            throw new SBIFClientException("Problems communicating with: $path", hce)
        }
        return response
    }

    /***
     * Permite obtener un listado con el valor del Dólar EE.UU. para cada día del año que se indique.
     * @param year
     * @return List < Dolar >
     */
    def List<Dolar> getDolares(int year) {
        Map params = ['year': year]
        return getResource(params, 'dolar', 'getDollars')
    }

    /***
     * Permite obtener un listado con el valor del Dólar EE.UU. para el actual año
     * @param year
     * @return List < Dolar >
     */
    def List<Dolar> getDolares() {
        Map params = [:]
        def instance = Calendar.getInstance();
        params << ['year': instance.get(Calendar.YEAR)]
        return getResource(params, 'dolar', 'getDollars')
    }

    /***
     * Permite obtener un listado con el valor del Dólar EE.UU. para cada día del mes del año que se indique.
     * @param year
     * @param month
     * @return List < Dolar >
     */
    def List<Dolar> getDolares(int year, int month) {
        Map params = ['year': year, 'month': month]
        return getResource(params, 'dolar', 'getDollars')
    }

    /***
     * Permite obtener un listado con el valor del Dólar EE.UU. para dia mes y año que se indique.
     * @param year
     * @param month
     * @param day
     * @return List < Dolar >
     */
    def Dolar getDolares(int year, int month, int day) {
        Map params = ['year': year, 'month': month, 'day': day]
        return getResource(params, 'dolar', 'getDollars').first()
    }

    /***
     * Permite obtener un listado con el valor del Dólar EE.UU. para los años siguientes al año que se indique.
     * @return List < Dolar >
     */
    def List<Dolar> getDolaresPosteriores(int year) {
        return getResource(['year': year], 'dolar', '/posteriores', 'getDollars')
    }

    /***
     * Permite obtener un listado con el valor del Dólar EE.UU. para cada día del mes del año que se indique.
     * @param year
     * @param month
     * @return List < Dolar >
     */
    def List<Dolar> getDolaresPosteriores(int year, int month) {
        Map params = ['year': year, 'month': month]
        return getResource(params, 'dolar', '/posteriores', 'getDollars')
    }

    /***
     * Permite obtener un listado con el valor del Dólar EE.UU. para los años anteriores al año que se indique.
     * @return List < Dolar >
     */
    def List<Dolar> getDolaresAnteriores(int year) {
        return getResource(['year': year], 'dolar', '/anteriores', 'getDollars')
    }

    /***
     * Permite obtener un listado con el valor del Dólar EE.UU. para mes y año que se indique.
     * @param year
     * @param month
     * @return List < Dolar >
     */
    def List<Dolar> getDolaresAnteriores(int year, int month) {
        Map params = ['year': year, 'month': month]
        return getResource(params, 'dolar', '/anteriores', 'getDollars')
    }

    /***
     * Permite obtener un listado del Dólar EE.UU. para cada uno de los días
     * incluidos dentro de los años que se indiquen en los parámetros.
     * @param year
     * @param year2
     * @return
     */
    def List<Dolar> getDolaresPeriodo(int year, int year2) {
        Map params = ['year': year, 'year2': year2]
        return getResource(params, 'dolar', '/periodo', 'getDollars')

    }

    /***
     *  Obtiene un recurso segun parametros, path, recurso y nombre del metodo.
     * @param params
     * @param path
     * @param resource
     * @param methodName
     * @return
     */
    private List getResource(Map params = [:], String path, String resource, String methodName) {
        String url = buildURL(params, "/${path}", "${resource}")
        return "$methodName"(url)
    }

    /***
     * Obtiene un recurso segun parametros, path y nombre del metodo.
     * @param params
     * @param path
     * @param methodName
     * @return
     */
    private List getResource(Map params = [:], String path, String methodName) {
        return getResource(params, path, new String(), methodName)
    }

    /***
     * Permite obtener un el valor del Dólar EE.UU. para el día actual.
     * @return Dolar
     */
    def Dolar getDolar() {
        def instance = Calendar.getInstance();
        Map params = [:]
        params << ['year': instance.get(Calendar.YEAR)]
        params << ['mont': instance.get(Calendar.MONTH) + 1]
        params << ['day': instance.get(Calendar.DAY_OF_MONTH)]
        return getResource(params, 'dolar', 'getDollars').first()
    }

    /***
     * Permite obtener un listado con el valor de todos los tipos de TMC para el año solicitado
     * @param year
     * @return List<TMC>
     */
    def List<TMC> getTMCByYear(int year) {
        Map params = ['year': year]
        return getResource(params, 'tmc', 'getTCM')
    }

    /***
     * Permite obtener un listado con el valor de todos los tipos de TMC para el año  y mes solicitado
     * @param year
     * @param month
     * @return List<TMC>
     */
    def List<TMC> getTMCByYearAndMonth(int year, int month) {
        Map params = ['year': year, 'month':month]
        return getResource(params, 'tmc', 'getTCM')
    }

    /***
     * Permite obtener un listado con el valor de todos los tipos de TMC anteriores para el año  y mes solicitado
     * @param year
     * @param month
     * @return List<TMC>
     */
    def List<TMC> getTMCByPreviousYearAndMonth(int year, int month) {
        Map params = ['year': year, 'month':month]
        return getResource(params, 'tmc','/anteriores','getTCM')
    }

    /***
     * Permite obtener un listado con el valor de todos los tipos de TMC posteriores para el año  y mes solicitado
     * @param year
     * @param month
     * @return List<TMC>
     */
    def List<TMC> getTMCByLaterYearAndMonth(int year, int month) {
        Map params = ['year': year, 'month':month]
        return getResource(params, 'tmc','/posteriores','getTCM')
    }

    /***
     * Permite obtener un listado con el valor de todos los tipos de TMC periodo  para los años y meses solicitados
     * @param year
     * @param month
     * @return List<TMC>
     */
    def List<TMC> getTMCByPeriodYearAndMonth(int year, int month, int year2, int month2) {
        Map params = ['year': year, 'month':month, 'year2':year2, 'month2':month2]
        return getResource(params, 'tmc','/periodo','getTCM')
    }

    /***
     * Llama al recurso TMC,parseando la respuesta en formato json
     * @param url
     * @return List < TMC >
     */
    private List<TMC> getTCM(String url) {
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

    /****
     * Llama al recurso dolar,parseando una lista en formato json
     * @param url
     * @return List < Dolar >
     */
    private List<Dolar> getDollars(String url) {
        def dolares = call(url).json.Dolares
        def list = []
        dolares.each() { data ->
            list << new Dolar(fecha: Date.parse('yyyy-MM-dd', data.Fecha),
                    valor: (data.Valor != null && data.Valor.trim().length() > 0) ? Double.parseDouble(data.Valor.trim().replaceAll(",", '.')) : 0)
        }
        return list
    }

    /****
     * Construte una url request segun parametros y path
     * @param params
     * @param path ruta
     * @return url al recurso api
     */
    private String buildURL(Map params, String path) {
        return buildURL(params, path, new String(""))
    }

    /***
     *
     * @param params
     * @param path
     * @param resource
     * @return
     */
    private String buildURL(Map params, String path, String resource) {
        return path + resource + buildDate(params) + "?apikey=${apikey}"
    }

    /***
     * Construye una url segun parametros.
     * @param params
     * @param includeDay :true
     * @return url
     */
    private String buildDate(Map params = [:], boolean includeDay = true) {
        def url = ''
        def day = '', month = '', year = '', year2 = '', month2='', period =''
        def instance = Calendar.getInstance();

        if (params.size() == 0)
            return url

        if (params.day) {
            if (includeDay) {
                day = "/dias/${params.day}"
            }
            day << "/${params.day}"
        }

        if (params.month)
            month = "/${params.month}"
        else if (day)
            month = "/${instance.get(Calendar.MONTH) + 1}"

        if (params.year)
            year = "/${params.year}"
        else if (month)
            year = "/${instance.get(Calendar.YEAR)}"


        if (params.year2) {
            year2 = "/${params.year2}"
            if (params.month2) {
                month2 = "/${params.month2}"
                return year << month << year2 << month2
            }
        }


        return year << year2 << month << month2 << day
    }


}
