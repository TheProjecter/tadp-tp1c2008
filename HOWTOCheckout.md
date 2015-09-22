HOWTO para integrar maven2 con tu IDE y hacer un checkout

## Introduccion ##

El objetivo de esta pagina es tener una referencia de como hacer para hacer un check out del proyecto en los IDEs mas comunes. Y si es necesario instalar el plugin de maven (mevenide)

Todavia esta incompleta, si ven que algo esta mal o incompleto por favor actualizarlo.


## Como se checkout-ea con el Elipse? ##

  1. Plugin maven y svn. (a version 3.3 Europa ya lo trae incluido)
  1. File -> New -> Project
  1. SVN -> Checkout Projects from SVN
  1. Create a new repository location
  1. Url: https://tadp-tp1c2008.googlecode.com/svn/trunk/
  1. Accept a todo lo que pregunta
  1. Username: el mismo que el mail de google (jcfandino para mi)
  1. Password: la que aparece en la pagina del proyecto
  1. Save password
  1. Seleccionar tp. Next
  1. Checkout as a project configured using the New Project Wizard. Finish.
  1. Maven -> Project
  1. Project name: tadp-tp1c2008. Next
  1. Version 1.0-SNAPSHOT
  1. Year 2008
  1. Organization Grupo6 y el resto de los datos
  1. Aceptar y listo.

## Como hacerlo con Netbeans ##

  1. Plugin maven. Ver Anexo A
  1. Versioning -> Subversion -> Checkout
  1. URL: https://tadp-tp1c2008.googlecode.com/svn/trunk/
  1. User: el de google
  1. Password: el de la pagina
  1. Save pass. Next.
  1. Repository folder trunk/tp
  1. Skip tp and checkout only its content. Finish.
  1. Aca no se como seguir, usar sentido comun y completar la wiki

## Anexo A instalar maven en netbeans ##

  1. Tools -> Plugins
  1. Available Plugins -> Maven -> Install
  1. Next. Accept. Install.