package project.jobseekerplatform.Configuration;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import project.jobseekerplatform.Model.dto.PostDto;
import project.jobseekerplatform.Model.dto.UserDtoBasic;
import project.jobseekerplatform.Model.entities.Post;

@Configuration
public class Config {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper= new ModelMapper();
        modelMapper.addConverter(postConverter);
        return modelMapper;
    }


    private final ModelMapper modelMapper;
    public Config(@Lazy ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
    Converter<Post, PostDto> postConverter = new Converter<Post, PostDto>() {
        @Override
        public PostDto convert(MappingContext<Post, PostDto> context) {
            Post source = context.getSource();
            return new PostDto(source.getId(), source.getContent(), source.getPostedDate(),
                    source.getImages(), modelMapper.map(source.getUser(), UserDtoBasic.class),
                    source.getLikes(), source.getComment());
        }
    };
}
