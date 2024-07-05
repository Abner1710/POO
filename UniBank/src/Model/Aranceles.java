package Model;

import javax.swing.JOptionPane;

// Clase abstracta para Aranceles
public abstract class Aranceles {
    private boolean pagado = false;

    public boolean isPagado() {
        return pagado;
    }

    public void setPagado(boolean pagado) {
        this.pagado = pagado;
    }

    public abstract void pagar(Cuenta cuenta);
}

class ArancelMatricula extends Aranceles {
    private static final double COSTO = 200;

    @Override
    public void pagar(Cuenta cuenta) {
        if (cuenta.getSaldo() >= COSTO && !isPagado()) {
            cuenta.retirar(COSTO);
            setPagado(true);
            JOptionPane.showMessageDialog(null, "Pago de matrícula realizado. Saldo actual: C$ " + cuenta.getSaldo());
        } else if (isPagado()) {
            JOptionPane.showMessageDialog(null, "El arancel de matrícula ya fue pagado.");
        } else {
            JOptionPane.showMessageDialog(null, "Saldo insuficiente para pagar la matrícula.");
        }
    }
}

class ArancelLaboratorio extends Aranceles {
    private static final double COSTO = 100;

    @Override
    public void pagar(Cuenta cuenta) {
        if (cuenta.getSaldo() >= COSTO && !isPagado()) {
            cuenta.retirar(COSTO);
            setPagado(true);
            JOptionPane.showMessageDialog(null, "Pago de laboratorio realizado. Saldo actual: C$ " + cuenta.getSaldo());
        } else if (isPagado()) {
            JOptionPane.showMessageDialog(null, "El arancel de laboratorio ya fue pagado.");
        } else {
            JOptionPane.showMessageDialog(null, "Saldo insuficiente para pagar el laboratorio.");
        }
    }
}

class ArancelBiblioteca extends Aranceles {
    private static final double COSTO = 50;

    @Override
    public void pagar(Cuenta cuenta) {
        if (cuenta.getSaldo() >= COSTO && !isPagado()) {
            cuenta.retirar(COSTO);
            setPagado(true);
            JOptionPane.showMessageDialog(null, "Pago de biblioteca realizado. Saldo actual: C$ " + cuenta.getSaldo());
        } else if (isPagado()) {
            JOptionPane.showMessageDialog(null, "El arancel de biblioteca ya fue pagado.");
        } else {
            JOptionPane.showMessageDialog(null, "Saldo insuficiente para pagar la biblioteca.");
        }
    }
}
