package com.media.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.media.models.Aluno;
import com.media.models.Disciplina;
import com.media.models.Nota;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "API REST Media Pondera")
@CrossOrigin(origins = "*")
public class MediaResource {

	@PostMapping("/calcularMedia")
	@ApiOperation(value = "Retorna a Media ponderada dos Alunos")
	public ArrayList<Double> calcularMedia(@RequestBody List<Aluno> alunos) {

		ArrayList<Double> notasMultiplicadas = new ArrayList();
		ArrayList<Double> mediaPonderada = new ArrayList();
		double notasSomadas = 0;
		for (Aluno aluno : alunos) {

			for (Disciplina disciplinasAluno : aluno.getDisciplinas()) {

				List<Nota> notasDoAlunoNaDisciplina = disciplinasAluno.getNotas();
				for (Nota notas : notasDoAlunoNaDisciplina) {
					notasMultiplicadas.add(notas.getProva() * notas.getPeso());
				}
				int aux = 0;
				int i = 1;

				notasSomadas += notasMultiplicadas.get(i) + notasMultiplicadas.get(aux);
				notasSomadas = notasSomadas + notasMultiplicadas.get(2);

				mediaPonderada.add(notasSomadas / notasDoAlunoNaDisciplina.size());

				notasMultiplicadas.clear();
				notasDoAlunoNaDisciplina.clear();
				notasSomadas = 0;
				aux++;

			}

		}

		return mediaPonderada;
	}

}
