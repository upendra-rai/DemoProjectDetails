package com.demo.project.batch.config;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import com.demo.project.dao.BookDao;
import com.demo.project.models.Book;

@Configuration
@EnableBatchProcessing

public class BatchConfig {

	private JobBuilderFactory jobBuilderFactory;

	private StepBuilderFactory stepBuilderFactory;

	private BookDao bookDao;

	@Bean
	public FlatFileItemReader<Book> itemReader() {
		FlatFileItemReader<Book> fileReader = new FlatFileItemReader<>();
		fileReader.setResource(new FileSystemResource("src/main/resources/test.txt"));
		fileReader.setName("Flat File Reader");
		fileReader.setLinesToSkip(1);
		fileReader.setLineMapper(getLineMapper());
		return fileReader;
	}

	@Bean
	private LineMapper<Book> getLineMapper() {
		DefaultLineMapper<Book> defaultMapper = new DefaultLineMapper<>();

		DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
		lineTokenizer.setDelimiter(",");
		lineTokenizer.setStrict(false);
		lineTokenizer.setNames(new String[] { "id", "name", "author" });

		BeanWrapperFieldSetMapper<Book> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
		fieldSetMapper.setTargetType(Book.class);
		defaultMapper.setLineTokenizer(lineTokenizer);
		defaultMapper.setFieldSetMapper(fieldSetMapper);
		return defaultMapper;
	}

	public BatchConfig(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
		super();
		this.jobBuilderFactory = jobBuilderFactory;
		this.stepBuilderFactory = stepBuilderFactory;
	}

	public BookProcessor processor() {
		return new BookProcessor();
	}

	public RepositoryItemWriter<Book> writer() {
		RepositoryItemWriter<Book> writer = new RepositoryItemWriter<>();
		writer.setRepository(bookDao);
		writer.setMethodName("save");
		return writer;
	}
}
