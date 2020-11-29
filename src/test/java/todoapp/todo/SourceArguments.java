package todoapp.todo;

import org.junit.jupiter.params.provider.Arguments;

import java.util.Collection;
import java.util.Arrays;
import java.util.Iterator;
import java.util.stream.*;

import todoapp.todo.Tag.Color;

class SourceArguments {
	/**
     * Return a stream of default data to use for test.
     * @return a stream of strings.
     */
    static Stream<String> stringProvider() {
        return Stream.of(null, "", "Test");
	}
	
	/**
     * Return a stream of default data to use for test.
     * @return a stream of color.
     */
    static Stream<Color> colorProvider() {
        return Stream.of(null, Color.RED);
	}
	
	/**
     * Return a stream of default data to use for test.
     * @return a stream of couple (string, color).
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
     * @return a stream of couple (string, string, string, color).
     */
    static Stream<Arguments> todoCreationParametersProvider() {
		Collection<String> ss = Arrays.asList(stringProvider().toArray(String[]::new));
		Collection<Arguments> ts = Arrays.asList((Arguments[])tagCreationParametersProvider().toArray(Arguments[]::new));
		Stream.Builder<Arguments> res = Stream.builder();

		Iterator<String> sit1 = ss.iterator();
		while (sit1.hasNext()) {
			Iterator<String> sit2 = ss.iterator();
			String first = sit1.next();
			while (sit2.hasNext()) {
				Iterator<Arguments> tit = ts.iterator();
				String second = sit2.next();
				while (tit.hasNext()) {
					Object[] tag = tit.next().get();
					res.add(Arguments.of(first, second, (String)tag[0], (Color)tag[1]));
				}
			}
		}

        return res.build();
	}
}
