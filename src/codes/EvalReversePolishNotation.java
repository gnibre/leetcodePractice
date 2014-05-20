package codes;

import java.util.ArrayList;

public class EvalReversePolishNotation {

	public void go(){
		String[] tokens = {"0","3","/"};
		
		int res = evalRPN(tokens);
		
		System.out.println(" res : "+res);
		
	}
	
	
	
	private class Exp {
		int val;
		int op = 0;
		boolean isOP;

		public int eval(int a, int b) {
			int ret = 0;
			switch (op) {
			case 1:
				ret = a + b;
				break;
			case 2:
				ret = a - b;
				break;
			case 3:
				ret = a * b;
				break;
			case 4:
				ret = a / b;
				break;
			}
			return ret;
		}

		public Exp(String s) {
			if ("+".equals(s)) {
				op = 1;
			} else if ("-".equals(s)) {
				op = 2;
			} else if ("*".equals(s)) {
				op = 3;
			} else if ("/".equals(s)) {
				op = 4;
			}
			if (op > 0) {
				isOP = true;
			} else {
				isOP = false;
				val = Integer.valueOf(s);
			}
		}

		public Exp(int v) {
			val = v;
			op = 0;
			isOP = false;
		}
	}

	public int evalRPN(String[] tokens) {

		ArrayList<Exp> eal = new ArrayList<Exp>();
		Exp e;
		Exp ea, eb;
		int eval;
		for (String s : tokens) {
			e = new Exp(s);
			if (e.isOP) {
				//eb first.
				eb = eal.remove(eal.size() - 1);
				ea = eal.remove(eal.size() - 1);
				eval = e.eval(ea.val, eb.val);
				e = new Exp(eval);
				eal.add(e);
			} else {
				eal.add(e);
			}
		}

		Exp res = eal.get(0);
		return res.val;

	}
}
