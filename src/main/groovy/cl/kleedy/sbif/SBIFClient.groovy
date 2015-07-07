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
     * Get method
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
        return getDolares(params)
    }

    /***
     * Permite obtener un listado con el valor del Dólar EE.UU. para los años siguientes al año que se indique.
     * @return List < Dolar >
     */
    def List<Dolar> getDolaresPosteriores(int year) {
        return getDolares(['year': year], '/dolar/posteriores')
    }

    /***
     * Permite obtener un listado con el valor del Dólar EE.UU. para cada día del mes del año que se indique.
     * @param year
     * @param month
     * @return List < Dolar >
     */
    def List<Dolar> getDolaresPosteriores(int year, int month) {
        Map params = ['year': year, 'month': month]
        return getDolares(params, '/dolar/posteriores')
    }

    /***
     * Permite obtener un listado con el valor del Dólar EE.UU. para los años anteriores al año que se indique.
     * @return List < Dolar >
     */
    def List<Dolar> getDolaresAnteriores(int year) {
        return getDolares(['year': year], '/dolar/anteriores')
    }

    /***
     * Permite obtener un listado con el valor del Dólar EE.UU. para cada día del mes del año que se indique.
     * @param year
     * @param month
     * @return List < Dolar >
     */
    def List<Dolar> getDolares(int year, int month) {
        Map params = ['year': year, 'month': month]
        return getDolares(params)
    }

    /***
     * Permite obtener un listado con el valor del Dólar EE.UU. para mes y año que se indique.
     * @param year
     * @param month
     * @return List < Dolar >
     */
    def List<Dolar> getDolaresAnteriores(int year, int month) {
        Map params = ['year': year, 'month': month]
        return getDolares(params, 'dolar/anteriores')
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
        return getDolares(params).first()
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
        return getDolares(params, 'dolar/periodo')

    }

    /***
     * Permite obtener un listado con el valor del Dólar EE.UU. para una fecha específica.
     * @param params
     * @return List < Dolar >  si no hay argumetos, retorna el valor del dolar al dia de hoy.
     */
    def List<Dolar> getDolares(Map params = [:], String path = '/dolar') {
        return buildURL(params, path)
    }

    /****
     * Construte una url request segun paramtros y path
     * @param params
     * @param path ruta
     * @return List < Dolar >
     */
    private List<Dolar> buildURL(Map params, String path) {
        def response = call(path + buildDate(params) + "?apikey=${apikey}")
        def dolares = response.json.Dolares
        def list = []
        dolares.each() { data ->
            list << new Dolar(fecha: Date.parse('yyyy-MM-dd', data.Fecha),
                    valor: (data.Valor != null && data.Valor.trim().length() > 0) ? Double.parseDouble(data.Valor.trim().replaceAll(",", '.')) : 0)
        }
        return list
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
        return getDolares(params).first()

    }

    /***
     * Construye una url segun parametros.
     * @param params
     * @param includeDay
     * @return url
     */
    private String buildDate(Map params = [:], boolean includeDay = true) {
        def url = ''
        def day = '', month = '', year = '', year2 = ''
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

        if (params.year2)
            year2 = "/${params.year2}"


        return year << year2 << month << day
    }


}
