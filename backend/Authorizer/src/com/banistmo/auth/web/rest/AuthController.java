package com.banistmo.auth.web.rest;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.MultipartConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.banistmo.auth.jpa.dao.IRoleDao;
import com.banistmo.auth.jpa.dao.IUserDao;
import com.banistmo.auth.jpa.dao.impl.RoleDaoImpl;
import com.banistmo.auth.jpa.entity.User;
import com.banistmo.auth.web.utils.Credentials;
import com.banistmo.auth.web.utils.LdapAuth;

@Service
@RestController
public class AuthController {

	@Autowired
	@Qualifier("userDao")
	private IUserDao userDao;

	@Autowired
	@Qualifier("roleDao")
	private IRoleDao roleDao;

	// @RequestMapping("/")
	// public String invokeProcess() {
	// String msg = "Hasta la vista... baby!!";
	// //RoleDaoImpl roleDaoImpl = new RoleDaoImpl();
	// try {
	// //roleDaoImpl.getAll();
	// //msg = roleDaoImpl.getRoleTest();
	// msg = this.roleDao.getTest();
	// msg += " " + this.userDao.getTest();
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// return msg;
	// }

	static protected List<String> myList = new ArrayList<String>();

	// Ruta del Arbol LDAP
	static {
		/*
		 * myList.add(
		 * ",OU=PA Standard Users,OU=PA CWD Users,OU=PA Users,OU=PA,DC=banistmo,DC=corp"
		 * ); myList.add(
		 * ",OU=PA Dev Users,OU=PA CWD Users,OU=PA Users,OU=PA,DC=banistmo,DC=corp"
		 * ); myList.add(",OU=PA CS Users,OU=PA Users,OU=PA,DC=banistmo,DC=corp"
		 * ); myList.add(
		 * ",OU=PA Management Accounts,OU=PA Management,OU=PA,DC=banistmo,DC=corp"
		 * );
		 *
		 * myList.add(
		 * ",OU=PA Dev Test Users,OU=Users,OU=PA Test Environment,OU=PA,DC=banistmo,DC=corp"
		 * ); myList.add(
		 * ",OU=PA Standard Test Users,OU=Users,OU=PA Test Environment,OU=PA,DC=banistmo,DC=corp"
		 * );
		 */

		myList.add(",OU=PA Dev Test Users,OU=Users,OU=PA Test Environment,OU=PA,DC=banistmo,DC=corp");
		myList.add(",OU=PA Standard Users,OU=PA CWD Users,OU=PA Users,OU=PA,DC=banistmo,DC=corp");
		myList.add(",OU=PA Dev Users,OU=PA CWD Users,OU=PA Users,OU=PA,DC=banistmo,DC=corp");
		myList.add(",OU=PA CS Users,OU=PA Users,OU=PA,DC=banistmo,DC=corp");
		myList.add(",OU=PA Management Accounts,OU=PA Management,OU=PA,DC=banistmo,DC=corp");
		myList.add(",OU=PA Dev Test Users,OU=Users,OU=PA Test Environment,OU=PA,DC=banistmo,DC=corp");
		myList.add(",OU=PA Standard Test Users,OU=Users,OU=PA Test Environment,OU=PA,DC=banistmo,DC=corp");

		// GRUPO EN EL QUE SE ENCUENTRAN LOS USARIOS TATA
		// myList.add(",OU=CO Provider Users,OU=CO,DC=banistmo,DC=corp");
		//
		// myList.add(",OU=PA Users,OU=PA,DC=banistmo,DC=corp");
		// myList.add(",OU=Users,OU=PA,DC=banistmo,DC=corp");
		// myList.add(",OU=PA-Users,OU=PA Groups,OU=PA,DC=banistmo,DC=corp");
		// myList.add(",OU=PA Standard Users,OU=PA CWD Users,OU=PA
		// Users,OU=PA,DC=banistmo,DC=corp");
		// myList.add(",OU=PA CS Users,OU=PA Users,OU=PA,DC=banistmo,DC=corp");
		//
		// myList.add(",OU=CO Groups,OU=CO,DC=banistmo,DC=corp");
		// myList.add(",OU=PA Standard Users,OU=PA CWD Users,OU=PA
		// Users,OU=PA,DC=banistmo,DC=corp");
		// myList.add(",OU=PA Dev Users,OU=PA CWD Users,OU=PA
		// Users,OU=PA,DC=banistmo,DC=corp");
	}

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public @ResponseBody User authenticate(@RequestBody Credentials credentials) throws Exception {
		// System.out.println(credentials.getUsername() + " " +
		// credentials.getPassword());
		//
		// User user = new User();
		// userModel.setId(1);
		// userModel.setPhone_number("123456789");
		// userModel.setEmail("luis.javier@tcs.com");
		// userModel.setUsername("luisjavierjn");
		// userModel.setArea("Dpto Informatica");

		/**************************************************/

		System.out.println(credentials.getUsername() + " " + credentials.getPassword());

		User user = null;

		LdapAuth ldapAuth = null;
		String nm = credentials.getUsername();
		String pd = credentials.getPassword();

		// String server="ldap://banistmo.corp:389";
		// String server="ldap://10.67.240.19:389";
		String server = "ldap://166.26.6.11:389";
		// LDAP Produccion Banistmo obtenido desde el archivo .properties
		// String server = getPropertySrv("ldapbanismto");

		// USUARIO AUTENTICACION
		String dn = "CN=" + nm;

		// tipo de autentuicacion simple o por SSL
		String tipoAth = "simple";

		for (String rutaArbolLdap : myList) {
			ldapAuth = new LdapAuth(server, dn + rutaArbolLdap, tipoAth, dn, pd);
			if (ldapAuth.isAutenticado()) {
				System.out.println("server: " + server + " dn: " + dn + " rutaArbolLdap: " + rutaArbolLdap + " tipoAth: " + tipoAth + " dn: " + dn + " pd: " + pd);
				/* El Usuario ha sido autenticado. */
				System.out.println("El Usuario ha sido autenticado");
				/**************************************************************/
				//user = new User();
				user = this.userDao.getByUsername(credentials.getUsername());	
				
				if (user != null) {
					if (user.getStatus() == 1) {
						System.out.println("El usuario está activo en el Sistema");
					} else {
						System.out.println("El usuario no está activo en el Sistema");
					}
				} else {
					System.out.println("El Usuario no tiene permisos para ingresar al Módulo");
				}
				
				break;
			}
		}

		// Validacion contra el LDAP
		if (!ldapAuth.isAutenticado()) {
			/* El Usuario o Contraseña es inválido */
			System.out.println("El Usuario o Contraseña es inválido");
		}

		/**************************************************/

		// User user = null;
		// try {
		// user = doLogin(credentials);
		// } catch (Exception e) {
		// e.printStackTrace();
		// }

		/**************************************************/

		// User user = new User();
		// user.setId(1);
		// user.setPhone_number("50011111");
		// user.setEmail("banpaldap01@banistmo.com");
		// user.setUsername("userldap");
		// user.setArea("Banistmo");

		return user;
	}

	public User doLogin(Credentials credentials) throws Exception {
		System.out.println(credentials.getUsername() + " " + credentials.getPassword());
		User user = this.userDao.getByUsername(credentials.getUsername());

		if (user != null) {
			if (user.getStatus() == 1) {
				/* El Usuario está activo en el Sistema */
				LdapAuth ldapAuth = null;
				String nm = credentials.getUsername();
				String pd = credentials.getPassword();

				// String server="ldap://banistmo.corp:389";
				String server = "ldap://10.67.240.19:389";
				// LDAP Produccion Banistmo obtenido desde el archivo
				// .properties
				// String server = getPropertySrv("ldapbanismto");

				// USUARIO AUTENTICACION
				String dn = "CN=" + nm;

				// tipo de autentuicacion simple o por SSL
				String tipoAth = "simple";

				for (String rutaArbolLdap : myList) {
					ldapAuth = new LdapAuth(server, dn + rutaArbolLdap, tipoAth, dn, pd);
					if (ldapAuth.isAutenticado()) {
						/* El Usuario ha sido autenticado. */
						System.out.println("El Usuario ha sido autenticado");
						user.setAuthorized(1);
						break;
					}
				}

				// Validacion contra el LDAP
				if (!ldapAuth.isAutenticado()) {
					/* El Usuario o Contraseña es inválido */
					System.out.println("El Usuario o Contraseña es inválido");
					user.setAuthorized(2);
				}
			} else {
				/* El usuario no está activo en el Sistema */
				System.out.println("El usuario no está activo en el Sistema");
				user.setAuthorized(3);
			}
		} else {
			/* El Usuario no tiene permisos para ingresar al Módulo */
			System.out.println("El Usuario no tiene permisos para ingresar al Módulo");
			user = new User();
			user.setAuthorized(4);
		}

		if (user.getAuthorized() != 4) {
			this.userDao.updateUser(user);
		}

		return user;
	}
}
