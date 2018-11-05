package s4h.event;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import s4h.entity.LangStatisticsEntity;
import s4h.repository.LangStatJpaRepository;

@Component
@Validated
public class LangStatisticsHandler {

    private LangStatJpaRepository repo;

    public LangStatisticsHandler(LangStatJpaRepository repo) {
	this.repo = repo;
    }

    @EventListener
    @Async
    public void handleImportedProducts(@NotNull @Valid LangImportEvent event) {
	repo.save(LangStatisticsEntity.from(event));
    }

}
