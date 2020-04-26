package com.futago.studioghibli.repository.custom;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.futago.studioghibli.entity.FeatureFilm;
import com.futago.studioghibli.rest.filter.FeatureFilmFilter;

public class FeatureFilmRepositoryCustomImpl implements FeatureFilmRepositoryCustom {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<FeatureFilm> getByFilter(FeatureFilmFilter filter) {
		CriteriaBuilder cb = em.getCriteriaBuilder();

		CriteriaQuery<FeatureFilm> criteriaQuery = cb.createQuery(FeatureFilm.class);
		Root<FeatureFilm> featureFilm = criteriaQuery.from(FeatureFilm.class);

		List<Predicate> predicates = getPredicates(cb, featureFilm, filter);
		criteriaQuery.select(featureFilm).where(predicates.toArray(new Predicate[predicates.size()]));
		criteriaQuery.orderBy(cb.desc(featureFilm.get("year")));

		TypedQuery<FeatureFilm> query = em.createQuery(criteriaQuery);
		return query.setMaxResults(100).getResultList();
	}

	private List<Predicate> getPredicates(CriteriaBuilder cb, Root<FeatureFilm> recurso, FeatureFilmFilter filter) {
		List<Predicate> predicates = new ArrayList<>();

		if (filter != null) {
			if (filter.getDirector() != null) {
				predicates.add(cb.and(cb.equal(recurso.get("director"), filter.getDirector())));
			}

			if (filter.getYear() != null) {
				predicates.add(cb.and(cb.equal(recurso.get("year"), filter.getYear())));
			}

			if (filter.getTitle() != null) {
				predicates.add(cb.and(cb.equal(recurso.get("portuguese"), filter.getTitle())));
			}
		}

		return predicates;
	}
}