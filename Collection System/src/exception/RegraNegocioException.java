package exception;

/**
 * Exceção customizada para capturar violações de regras de negócio
 * sem acoplamento com a interface gráfica.
 */
public class RegraNegocioException extends RuntimeException {
    public RegraNegocioException(String mensagem) {
        super(mensagem);
    }
}