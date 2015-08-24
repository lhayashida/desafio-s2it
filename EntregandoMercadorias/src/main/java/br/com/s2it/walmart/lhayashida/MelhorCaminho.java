package br.com.s2it.walmart.lhayashida;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.json.JSONException;
import org.json.JSONObject;

import br.com.s2it.walmart.lhayashida.business.PercorreCaminho;
import br.com.s2it.walmart.lhayashida.entity.CaminhoCompleto;
import br.com.s2it.walmart.lhayashida.entity.Rota;

@Path("/melhorcaminho")
public class MelhorCaminho {
	
	@Path("{origem}/{destino}/{autonomia}/{custo}")
	@GET
	@Produces("application/json")
	public Response calculaMelhorCaminho(@PathParam("origem") String origem,
			@PathParam("destino") String destino,
			@PathParam("autonomia") BigDecimal autonomia,
			@PathParam("custo") BigDecimal custo) throws JSONException {

		PopulaBaseDados bd = new PopulaBaseDados();
		List<Rota> mapa = bd.getMapa();
		PercorreCaminho percorreCaminho = new PercorreCaminho();
		
		List<CaminhoCompleto> todosCaminhos = percorreCaminho.pesquisaTodosCaminhos(origem, destino, mapa);
		Collections.sort(todosCaminhos);

		CaminhoCompleto melhorCaminho = todosCaminhos.get(0);
		StringBuilder caminho = new StringBuilder();
		for (Rota rota : melhorCaminho.getRota()) {
			caminho.append(rota);
			caminho.append(';');
		}
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("rota", caminho.toString());
		jsonObject.put("distanciatotal", melhorCaminho.getDistanciaTotal());
		jsonObject.put("custo", (new BigDecimal(melhorCaminho.getDistanciaTotal()).divide((autonomia.divide(custo)))));
		
		String result = "@Produces(\"application/json\") Resultado: \n\n" + jsonObject;
		return Response.status(200).entity(result).build();
	}

}
