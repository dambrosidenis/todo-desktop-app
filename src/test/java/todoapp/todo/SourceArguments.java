package todoapp.todo;

import org.junit.jupiter.params.provider.Arguments;

import java.util.Collection;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Iterator;
import java.util.stream.*;

import todoapp.todo.Attribute.Color;

class SourceArguments {
	/**
     * Return a stream of default data to use for test.
     * @return a stream of String.
     */
    static Stream<String> stringProvider() {
        return Stream.of(null, "", "Test");
	}
	
	/**
     * Return a stream of default data to use for test.
     * @return a stream of Color.
     */
    static Stream<Color> colorProvider() {
        return Stream.of(null, Color.RED);
	}

	/**
     * Return a stream of default data to use for test.
     * @return a stream of LocalDateTime.
     */
    static Stream<LocalDateTime> dateProvider() {
        return Stream.of(null, LocalDateTime.now(), LocalDateTime.now().plusDays(3), LocalDateTime.now().minusDays(3));
	}
	
	/**
     * Return a stream of default data to use for test.
     * @return a stream of couple (String, Color).
     */
    static Stream<Arguments> tagCreationParametersProvider() {
		Collection<String> ss = Arrays.asList(stringProvider().toArray(String[]::new));
		Collection<Color> cs = Arrays.asList((Color[])colorProvider().toArray(Color[]::new));
		Stream.Builder<Arguments> res = Stream.builder();

		Iterator<String> sit = ss.iterator();
		while (sit.hasNext()) {
			Iterator<Color> cit = cs.iterator();
			String first = sit.next();
			while (cit.hasNext()) {
				res.add(Arguments.of(first, cit.next()));
			}
		}

        return res.build();
	}

	/**
     * Return a stream of default data to use for test.
     * @return a stream of couple (LocalDateTime, Color).
     */
    static Stream<Arguments> deadlineCreationParametersProvider() {
		Collection<LocalDateTime> localDTStream = Arrays.asList(dateProvider().toArray(LocalDateTime[]::new));
		Collection<Color> cs = Arrays.asList((Color[])colorProvider().toArray(Color[]::new));
		Stream.Builder<Arguments> res = Stream.builder();

		Iterator<LocalDateTime> localDTIterator = localDTStream.iterator();
		while (localDTIterator.hasNext()) {
			Iterator<Color> cit = cs.iterator();
			LocalDateTime first = localDTIterator.next();
			while (cit.hasNext()) {
				res.add(Arguments.of(first, cit.next()));
			}
		}

        return res.build();
	}

	/**
     * Return a stream of default data to use for test.
     * @return a stream of Tag.
     */
	static Stream<Tag> tagProvider() {
		Collection<Arguments> ts = Arrays.asList((Arguments[])tagCreationParametersProvider().toArray(Arguments[]::new));
		Stream.Builder<Tag> res = Stream.builder();

		res.add(null);

		Iterator<Arguments> tagIt = ts.iterator();
		while (tagIt.hasNext()) {
			Object[] tmp = tagIt.next().get();
			String text = (String)tmp[0];
			Color c = (Color)tmp[1];
			try {
				res.add(new Tag(text, c));
			} catch (Exception e) {}
		}

		return res.build();
	}
	
	/**
     * Return a stream of default data to use for test.
     * @return a stream of (String, String, Tag).
     */
    static Stream<Arguments> todoCreationParametersProvider() {
		Collection<String> ss = Arrays.asList(stringProvider().toArray(String[]::new));
		Collection<Tag> ts = Arrays.asList((Tag[])tagProvider().toArray(Tag[]::new));
		Stream.Builder<Arguments> res = Stream.builder();

		Iterator<String> sit1 = ss.iterator();
		while (sit1.hasNext()) {
			Iterator<String> sit2 = ss.iterator();
			String first = sit1.next();
			while (sit2.hasNext()) {
				Iterator<Tag> tagIt = ts.iterator();
				String second = sit2.next();
				while (tagIt.hasNext()) {
					Tag tag = tagIt.next();
					res.add(Arguments.of(first, second, tag));
				}
			}
		}

        return res.build();
	}

	/**
     * Return a stream of default data to use for test.
     * @return a stream of ToDo.
     */
	static Stream<ToDo> todoProvider() {
		Collection<Arguments> tds = Arrays.asList((Arguments[])todoCreationParametersProvider().toArray(Arguments[]::new));
		Stream.Builder<ToDo> res = Stream.builder();

		res.add(null);

		Iterator<Arguments> todoIt = tds.iterator();
		while (todoIt.hasNext()) {
			Object[] tmp = todoIt.next().get();
			String title = (String)tmp[0];
			String description = (String)tmp[1];
			Tag tag = (Tag)tmp[2];
			try {
				res.add(new ToDo(title, description, tag));
			} catch (Exception e) {}
		}

		return res.build();
	}

	/**
     * Return a stream of default data to use for test.
     * @return a stream of (ToDo, String).
     */
	static Stream<Arguments> todoAndStringProvider() {
		Collection<ToDo> tds = Arrays.asList((ToDo[])todoProvider().toArray(ToDo[]::new));
		Collection<String> ss = Arrays.asList((String[])stringProvider().toArray(String[]::new));
		Stream.Builder<Arguments> res = Stream.builder();

		Iterator<ToDo> todoIt = tds.iterator();
		while (todoIt.hasNext()) {
			Iterator<String> stringIt = ss.iterator();
			ToDo td = todoIt.next();
			while (stringIt.hasNext()) {
				res.add(Arguments.of(td, stringIt.next()));
			}
		}

        return res.build();
	}

	/**
     * Return a stream of default data to use for test.
     * @return a stream of (ToDo, Tag).
     */
	static Stream<Arguments> todoAndTagProvider() {
		Collection<ToDo> tds = Arrays.asList((ToDo[])todoProvider().toArray(ToDo[]::new));
		Collection<Tag> ts = Arrays.asList((Tag[])tagProvider().toArray(Tag[]::new));
		Stream.Builder<Arguments> res = Stream.builder();

		Iterator<ToDo> todoIt = tds.iterator();
		while (todoIt.hasNext()) {
			Iterator<Tag> tagIt = ts.iterator();
			ToDo td = todoIt.next();
			while (tagIt.hasNext()) {
				res.add(Arguments.of(td, tagIt.next()));
			}
		}

        return res.build();
	}
}
