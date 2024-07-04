import wave
import numpy as np

def analyze_waveform(wav_file_path, output_file_path, segment_duration_ms=10):
    with wave.open(wav_file_path, 'rb') as wav_file:
        # Extract basic parameters
        framerate = wav_file.getframerate()
        n_frames = wav_file.getnframes()

        # Read audio data
        audio_data = wav_file.readframes(n_frames)
        audio_samples = np.frombuffer(audio_data, dtype=np.int16)

        # Calculate the segment size
        duration = n_frames / framerate
        segment_size = int(framerate * segment_duration_ms / 1000)
        num_segments = 30
        print(num_segments)
        min_values = []
        max_values = []

        for i in range(num_segments):
            segment = audio_samples[i * segment_size:(i + 1) * segment_size]
            min_values.append(segment.min())
            max_values.append(segment.max())

        
        print(duration)
        with open(output_file_path, 'w') as f:
            f.write(f"Duration: {duration}\n")
            print(min_values)
            print(max_values)
            f.write("Min values: " + " ".join(map(str, min_values)) + "\n")
            f.write("Max values: " + " ".join(map(str, max_values)) + "\n")

def main():
    wav_file_path = 'resources\\audio\\for_test\\admin_20240626_180700.wav'  # Update this path to your WAV file
    output_file_path = 'resources\\audio\\for_test\\output_waveform_data.txt'  # Output file path
    analyze_waveform(wav_file_path, output_file_path)
    print(f"Waveform data saved to {output_file_path}")

if __name__ == '__main__':
    main()