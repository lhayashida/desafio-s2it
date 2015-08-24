package br.com.s2it.walmart.lhayashida;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.bson.Document;
import org.json.JSONObject;

import br.com.s2it.walmart.lhayashida.entity.Rota;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;

@Path("/basedados")
public class PopulaBaseDados {

	@Path("inserir")
	@POST
	@Consumes("text/xml")
	public Response insert(List<Rota> rotas) {
		
		MongoClient mongo = getConnection();
		MongoCollection<Document> collection = getCollection(mongo);
		
		for (Rota rota : rotas) {
			
			if (rota.getOrigem() == null || rota.getDestino() == null || rota.getDistancia() == null) {
				mongo.close();
				return Response.status(400).build();
			}
			
			Document document = new Document();
			document.put("origem", rota.getOrigem());
			document.put("destino", rota.getDestino());
			document.put("distancia", rota.getDistancia());
				
			collection.insertOne(document);
		}
				
		mongo.close();

		return Response.status(200).build();

	}
	
	@Path("limpar")
	@DELETE
	public Response drop() {

		MongoClient mongo = getConnection();
		MongoCollection<Document> collection = getCollection(mongo);
			
		collection.drop();
		
		closeConnection(mongo);

		return Response.status(200).build();

	}
	
	@Path("listar")
	@GET
	@Produces("application/json")
	public Response list() {

		MongoClient mongo = getConnection();
		MongoCollection<Document> collection = getCollection(mongo);
		
		StringBuilder mapa =  new StringBuilder();
		for (Document doc : collection.find()) {		
			Rota rota = new Rota();
			rota.setOrigem(doc.getString("origem"));
			rota.setDestino(doc.getString("destino"));
			rota.setDistancia(doc.getLong("distancia"));
			mapa.append(rota);
			mapa.append(" / ");			
		}
		
		closeConnection(mongo);
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("rota: ", mapa.toString());

		String result = "@Produces(\"application/json\") Resultado: \n\n" + jsonObject;
		return Response.status(200).entity(result).build();

	}

	public List<Rota> getMapa() {

		MongoClient mongo = getConnection();
		MongoCollection<Document> collection = getCollection(mongo);
		
		List<Rota> mapa =  new ArrayList<Rota>();
		for (Document doc : collection.find()) {		
			Rota rota = new Rota();
			rota.setOrigem(doc.getString("origem"));
			rota.setDestino(doc.getString("destino"));
			rota.setDistancia(doc.getLong("distancia"));
			mapa.add(rota);	
		}
		
		closeConnection(mongo);
		
		return mapa;

	}
	
	private MongoClient getConnection() {
		return new MongoClient("localhost", 27017);
	}
	
	private MongoCollection<Document> getCollection(MongoClient client) {
		return client.getDatabase("Intinerarios").getCollection("mapa");
	}
	
	private void closeConnection(MongoClient client){
		client.close();
	}

}
