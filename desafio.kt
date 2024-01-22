enum class Nivel { BASICO, INTERMEDIARIO, AVANCADO }

data class Usuario(val nome: String, val matricula: Int, var nivel: Nivel) {} //o usuario também tem nível, uma vez que posteriormente poderia aplicar alguma regra de négocio.

data class ConteudoEducacional(val nome: String, var duracao: Int = 15, val nivel: Nivel) //alterado o atributo nome para val, pois nao vai ser mais mudado e o atributo duracao para var, pois posteriormente pode ser mudado.

data class Formacao(val nome: String, val conteudos: List<ConteudoEducacional>, val nivel: Nivel) {

    val inscritos: MutableList<Usuario> = mutableListOf<Usuario>()
    val readOnlyInscritos: List<Usuario> = inscritos
        
    
    fun matricularUsuario(usuario: Usuario) { //essa função é para incluir apenas 1 usuario.  mesmo sabendo que a function com vararg poderia incluir 1 único elemento também, resolvi deixar as duas para fins didádicos.     
       inscritos.add(usuario)   
    }
    
    fun matricularVariosUsuarios(vararg usuarios: Usuario){ //já nessa é utilizada o vararg para adicionar vários usuarios ao mesmo tempo.
        for (u in usuarios) inscritos.add(u)
    }
    
    
    fun matriculados() : List<Usuario> {
        return readOnlyInscritos 
    }
}

fun main() {
    //DONE("Analise as classes modeladas para este domínio de aplicação e pense em formas de evoluí-las.")
    //DONE("Simule alguns cenários de teste. Para isso, crie alguns objetos usando as classes em questão.")
    
    val alunoA = Usuario("Rafael", 123, Nivel.AVANCADO)
    val alunoB = Usuario("Marina", 321, Nivel.BASICO)
    val alunoC = Usuario("João", 465, Nivel.AVANCADO)
    
    val conteudo1 = ConteudoEducacional(nome = "Conhecendo Kotlin parte 1", duracao = 25, nivel = Nivel.BASICO)
    val conteudo2 = ConteudoEducacional(nome = "Conhecendo Kotlin parte 2", nivel = Nivel.INTERMEDIARIO) // aqui assumindo o valor padrão para o atributo duração da aula
    
    
    
    val conteudoKotlin = listOf(conteudo1, conteudo2)
    
    
    val formacaoEmKotlin = Formacao(nome = "Formação em Kotlin", conteudos = conteudoKotlin, nivel = Nivel.INTERMEDIARIO)
    
    formacaoEmKotlin.matricularVariosUsuarios(alunoA, alunoB) // Utilizando o método com vararg
    formacaoEmKotlin.matricularUsuario(alunoC) //utlizando o método com argumento único
 
    
    println(formacaoEmKotlin.inscritos) //printa direto a lista mutável
    println(formacaoEmKotlin.matriculados()) //aqui printa a lista imutável(com capsulamento)
    
}
