/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

/**
 * Faz conexão com o banco de dados e suas funcionalidades CRUD
 */


import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class FilmesDao {
    
    private String url = "jdbc:mysql://localhost:3306/cenaflix"; //Nome da base de dados
    private String user = "root"; //nome do usuário do MySQL
    private String password = "1234"; //senha do MySQL 
    
    Connection conn;
    PreparedStatement st;
    ResultSet rs;
    
/**    fazendo conexão com o banco de dados
     * @return  */
    public boolean conectar(){
        
        try {
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
            return true;
            
        } catch (ClassNotFoundException | SQLException ex) {
            
            System.out.println("Erro ao conectar: " + ex.getMessage());
            return false;
        }
    }
    
/**    pesquisa e retorna o filme pelo nome
     * @param nome
     * @return  */
    public Filme getFilme (String nome){
      String sql = "SELECT * FROM filmes WHERE nome = ?";
      try {
                  
          PreparedStatement stmt = this.conn.prepareStatement(sql);
          stmt.setString(1, nome);
          rs = stmt.executeQuery();
        
          Filme filme = new Filme();
          
          rs.next(); 
          
          filme.setNome(rs.getString("nome"));
          filme.setDataLancamento(rs.getDate("datalancamento"));    
          filme.setCategoria(rs.getString("categoria"));    
          
          return filme;
          
          //tratando o erro, caso ele ocorra
      } catch (SQLException e) {
          System.out.println("erro: " + e.getMessage());
          return null;
      }
  }
    
    
    
/**    realiza a exclusão do filme por seu nome
     * @param nome */
    public void excluir (String nome){
                
                String sql = "DELETE FROM filmes WHERE nome = ?";
                try {
                    //esse trecho é igual ao método editar e inserir
                    PreparedStatement stmt = this.conn.prepareStatement(sql);
                    stmt.setString(1, nome);
                    
                    //Executando a query
                    stmt.execute();
                    //tratando o erro, caso ele ocorra
                } catch (SQLException e) {
                    System.out.println("Erro ao excluir filme: " + e.getMessage());
                }
                
            }
    
    
/**    atualiza dados do filme por seu nome
     * @param filme
     * @param nome */
    public void editar (Filme filme, String nome){
                //string sql com o código de update para o banco de dados
                String sql = "UPDATE filmes SET nome=?, datalancamento=?, categoria=? WHERE nome=?";
                try {
                    //esse trecho é igual ao método inserir
                    PreparedStatement stmt = conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,
                            ResultSet.CONCUR_UPDATABLE);
                    //Setando os parâmetros
                    stmt.setString(1, filme.getNome());
                    stmt.setDate(2, filme.getDataLancamento());
                    stmt.setString(3, filme.getCategoria());
                    
                    stmt.setString(4, nome);
                    //Executando a query
                    stmt.execute();
                    //tratando o erro, caso ele ocorra
                } catch (SQLException e) {
                    System.out.println("Erro ao editar filme: " + e.getMessage());
                }
            }
    
    
/**    gera uma lista filmes com todos os filmes listados no banco de dados
     * @return  */
    public List<Filme> getFilmes() {
                String sql = "SELECT * FROM filmes";
                
                try {
                    PreparedStatement stmt = this.conn.prepareStatement(sql);
                    rs = stmt.executeQuery();            
                    
                    List<Filme> listaFilmes = new ArrayList<>();
                    
                    while (rs.next()) { //.next retorna verdadeiro caso exista uma próxima posição dentro do array
                        Filme filme = new Filme();
                        
                        filme.setNome(rs.getString("nome"));
                        filme.setDataLancamento(rs.getDate("datalancamento"));    
                        filme.setCategoria(rs.getString("categoria"));    
                        
                        listaFilmes.add(filme);    
                    }
                    return listaFilmes;
                    
                    //Se o método entrar no "Catch" quer dizer que não encontrou nenhuma empresa, então damos um "return null"
                } catch (SQLException e) {
                    return null;
                }
            }
    
    
    
/**    salva o filme no banco de dados
     * @param filme
     * @return  */
    public int salvar(Filme filme){
        
        int status;
        
        try {
            
            st = conn.prepareStatement("INSERT INTO filmes (nome, datalancamento, categoria) VALUES(?,?,?)");
            st.setString(1,filme.getNome());
            st.setDate(2,filme.getDataLancamento());
            st.setString(3, filme.getCategoria());
            status = st.executeUpdate();
            return status; //retornar 1
            
        } catch (SQLException ex) {
            
            System.out.println("Erro ao conectar: " + ex.getMessage());
            return ex.getErrorCode();
        }
    }
    
   
/**  realiza a desconexão com o banco de dados */
    public void desconectar(){
        
        try {
            
            conn.close();
            
        } catch (SQLException ex) {
            
            //pode-se deixar vazio para evitar uma mensagem de erro desnecessária ao usuário
        }
    }
}