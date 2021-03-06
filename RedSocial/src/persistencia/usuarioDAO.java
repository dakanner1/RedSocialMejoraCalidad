package persistencia;

import org.springframework.stereotype.Component;



import org.apache.commons.codec.digest.DigestUtils;
import org.bson.BsonDocument;
import org.bson.BsonString;
import org.bson.BsonValue;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;

import dominio.Usuario;


@Component
public class usuarioDAO {
	
	/*public String show () throws Exception{//falta cerrar.... Este metodo equivale a un readAll
		MongoBroker broker = MongoBroker.get();
		MongoCollection<BsonDocument> usuarios = broker.getCollection("Usuarios");
		return usuarios.toString();
		
    }*/
	
	/*public void insert (Usuario usuario){
		BsonDocument bso = new BsonDocument();
		bso.append("nombre", new BsonString(usuario.getNombre()));
		bso.append("pwd", new BsonString(DigestUtils.md5Hex(usuario.getClave())));

		MongoBroker broker = MongoBroker.get();
		MongoCollection<BsonDocument> usuarios = broker.getCollection("Usuarios");
		usuarios.insertOne(bso);
	}*/

	/*public void insert (Usuario usuario, String pwd){
		BsonDocument bso = new BsonDocument();
		bso.append("nombre", new BsonString(usuario.getNombre()));
		bso.append("pwd", new BsonString(DigestUtils.md5Hex(pwd)));

		MongoBroker broker = MongoBroker.get();
		MongoCollection<BsonDocument> usuarios = broker.getCollection("Usuarios");
		usuarios.insertOne(bso);
	}

	public Usuario select(String nombre, String pwd) throws Exception {
		MongoBroker broker = MongoBroker.get();
		MongoCollection<BsonDocument> usuarios = broker.getCollection("Usuarios");
		BsonDocument criterio = new BsonDocument();
		criterio.append("nombre", new BsonString(nombre));
		criterio.append("pwd", new BsonString(DigestUtils.md5Hex(pwd)));
		FindIterable<BsonDocument> resultado=usuarios.find(criterio);
		BsonDocument usuario = resultado.first();
		if (usuario==null)
			throw new Exception("Credenciales incorrectos.");
		Usuario result = new Usuario(nombre);
		return result;
	}
	
	public void delete (Usuario usuario) throws Exception {
		BsonDocument bso = new BsonDocument();
		bso.append("nombre", new BsonString(usuario.getNombre()));

		MongoBroker broker = MongoBroker.get();
		MongoCollection<BsonDocument> usuarios = broker.getCollection("Usuarios");
		usuarios.deleteOne(bso);

	}
	
	public void update(String nombre, String pwdAntigua, String pwdNueva) throws Exception{

		MongoBroker broker = MongoBroker.get();
		MongoCollection<BsonDocument> usuarios = broker.getCollection("Usuarios");
		BsonDocument criterio = new BsonDocument();
		criterio.append("nombre", new BsonString(nombre));
		criterio.append("pwd", new BsonString(pwdAntigua));
		FindIterable<BsonDocument> resultado=usuarios.find(criterio);
		BsonDocument usuario = resultado.first();
		if (usuario==null)
			throw new Exception("Fall� la actualizaci�n de los datos del usuario.");

		BsonDocument actualizacion= new BsonDocument("$set", new BsonDocument("pwd", new BsonString(pwdNueva)));
		usuarios.findOneAndUpdate(usuario, actualizacion);
	}
	
	public void select(String nombre) throws Exception{
		MongoBroker broker = MongoBroker.get();
		MongoCollection<BsonDocument> usuarios = broker.getCollection("Usuarios");
		BsonDocument criterio = new BsonDocument();
		criterio.append("nombre", new BsonString(nombre));
		FindIterable<BsonDocument> resultado=usuarios.find(criterio);
		BsonDocument usuario = resultado.first();
		if (usuario==null)
			throw new Exception("Usuario no registrado.");
	}
	public String selectPwd(String nombre) throws Exception{
		
		BsonValue pwd;
		MongoBroker broker = MongoBroker.get();
		MongoCollection<BsonDocument> usuarios = broker.getCollection("Usuarios");
		BsonDocument criterio = new BsonDocument();
		criterio.append("nombre", new BsonString(nombre));
		FindIterable<BsonDocument> resultado=usuarios.find(criterio);
		BsonDocument usuario = resultado.first();
		if (usuario==null)
			throw new Exception("Usuario no registrado.");
		pwd=usuario.get("pwd");
		BsonString password=pwd.asString();
		String pwdFinal=password.getValue();
		return pwdFinal;
	}*/
	public static Boolean selectNombre(String nombre){
		
		BsonValue pwd;
		MongoBroker broker = MongoBroker.get();
		MongoCollection<BsonDocument> usuarios = broker.getCollection("Usuarios");
		BsonDocument criterio = new BsonDocument();
		criterio.append("nombre", new BsonString(nombre));
		FindIterable<BsonDocument> resultado=usuarios.find(criterio);
		BsonDocument usuario = resultado.first();
		if (usuario==null)
			return false;
		return true;
	}
	public static boolean login(Usuario user) {
		MongoBroker broker = MongoBroker.get();
		MongoCollection<BsonDocument> usuarios = broker.getCollection("Usuarios");
		BsonDocument criterio = new BsonDocument();
		criterio.append("nombre", new BsonString(user.getNombre()));
		criterio.append("pwd", new BsonString(DigestUtils.md5Hex(user.getPwd())));
		FindIterable<BsonDocument> resultado=usuarios.find(criterio);
		BsonDocument usuario = resultado.first();
		if (usuario==null)
			return false;
		return true;
	}

	public static void insert(Usuario usuario) {
		if(!selectNombre(usuario.getNombre())) {
			BsonDocument bso = new BsonDocument();
			bso.append("nombre", new BsonString(usuario.getNombre()));
			bso.append("pwd", new BsonString(DigestUtils.md5Hex(usuario.getPwd())));
			
			MongoBroker broker = MongoBroker.get();
			MongoCollection<BsonDocument> usuarios = broker.getCollection("Usuarios");
			usuarios.insertOne(bso);
		}
		
	}
	public static void delete (Usuario usuario) {
		BsonDocument bso = new BsonDocument();
		bso.append("nombre", new BsonString(usuario.getNombre()));

		MongoBroker broker = MongoBroker.get();
		MongoCollection<BsonDocument> usuarios = broker.getCollection("Usuarios");
		usuarios.deleteOne(bso);

	}
	
}